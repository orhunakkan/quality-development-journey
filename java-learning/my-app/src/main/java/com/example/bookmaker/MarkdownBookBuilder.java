package com.example.bookmaker;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;

/**
 * Reads Markdown files, converts them to HTML, and renders a single PDF book.
 */
public class MarkdownBookBuilder {

    private final Parser parser;
    private final HtmlRenderer renderer;
    private final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public MarkdownBookBuilder() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public List<Path> listMarkdownFiles(Path folder) throws IOException {
        if (!Files.isDirectory(folder)) {
            throw new IOException("Folder does not exist: " + folder);
        }

        List<Path> files = new ArrayList<>();
        try (var stream = Files.list(folder)) {
            stream.filter(path -> Files.isRegularFile(path) && hasMarkdownExtension(path))
                    .sorted()
                    .forEach(files::add);
        }

        return Collections.unmodifiableList(files);
    }

    public Path buildBook(BuildOptions options, Path outputFile, Consumer<String> progress) throws IOException {
        List<Path> markdownFiles = options.chapters();
        if (markdownFiles.isEmpty()) {
            throw new IOException("No markdown files selected");
        }

        notifyProgress(progress, "Reading markdown files...");
        String html = buildHtmlDocument(options);

        notifyProgress(progress, "Rendering PDF...");
        writePdf(html, outputFile, options.baseFolder());

        notifyProgress(progress, "Saved book to " + outputFile);
        return outputFile;
    }

    private String buildHtmlDocument(BuildOptions options) throws IOException {
        String title = options.title();
        String author = options.author();
        String date = options.date();

        StringBuilder body = new StringBuilder();
        body.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/>")
                .append("<title>").append(escapeHtml(title)).append("</title>")
                .append("<meta name=\"author\" content=\"").append(escapeHtml(author)).append("\"/>")
                .append("<meta name=\"date\" content=\"").append(escapeHtml(date)).append("\"/>")
                .append("<style>")
                .append(cssForTheme(options.theme()))
                .append(baseCss())
                .append("</style></head><body>");

        if (options.coverImage() != null) {
            body.append(buildCover(options));
        }

        body.append("<header class=\"book-header\"><h1>")
                .append(escapeHtml(title))
                .append("</h1>");
        if (!author.isBlank() || !date.isBlank()) {
            body.append("<p class=\"meta\">")
                    .append(!author.isBlank() ? escapeHtml(author) + " • " : "")
                    .append(!date.isBlank() ? escapeHtml(date) : "")
                    .append("</p>");
        }
        body.append("<p class=\"meta\">Generated ").append(timestampFormatter.format(LocalDateTime.now())).append("</p></header>");

        if (options.includeToc()) {
            body.append(buildToc(options.chapters()));
        }

        int index = 1;
        for (Path file : options.chapters()) {
            String markdown = Files.readString(file, StandardCharsets.UTF_8);
            Node doc = parser.parse(markdown);
            String html = renderer.render(doc);
            String heading = escapeHtml(stripExtension(file.getFileName().toString()));
            String id = "chapter-" + index;

            body.append("<section class=\"chapter\" id=\"").append(id).append("\"><h2>")
                    .append(heading)
                    .append("</h2>")
                    .append(html)
                    .append("</section>");
            index++;
        }

        body.append("</body></html>");
        return body.toString();
    }

    private void writePdf(String html, Path outputFile, Path baseFolder) throws IOException {
        Path parent = Objects.requireNonNullElse(outputFile.getParent(), outputFile.toAbsolutePath().getParent());
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (OutputStream out = Files.newOutputStream(outputFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            String base = baseFolder != null ? baseFolder.toUri().toString() : null;
            builder.withHtmlContent(html, base);
            builder.toStream(out);
            builder.run();
        } catch (Exception ex) {
            throw new IOException("Failed to render PDF", ex);
        }
    }

    private boolean hasMarkdownExtension(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        return name.endsWith(".md") || name.endsWith(".markdown");
    }

    private void notifyProgress(Consumer<String> progress, String message) {
        if (progress != null) {
            progress.accept(message);
        }
    }

    private String stripExtension(String name) {
        int dot = name.lastIndexOf('.');
        return dot > 0 ? name.substring(0, dot) : name;
    }

    private String escapeHtml(String text) {
        String escaped = text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
        return escaped;
    }

    private String buildCover(BuildOptions options) {
        StringBuilder cover = new StringBuilder();
        cover.append("<section class=\"cover\">");
        cover.append("<div class=\"cover-content\">");
        if (options.coverImage() != null) {
            cover.append("<div class=\"cover-image\" style=\"background-image:url('")
                    .append(options.coverImage().toUri())
                    .append("');\"></div>");
        }
        cover.append("<div class=\"cover-text\">")
                .append("<h1>").append(escapeHtml(options.title())).append("</h1>");
        if (!options.author().isBlank()) {
            cover.append("<p class=\"meta\">").append(escapeHtml(options.author())).append("</p>");
        }
        if (!options.date().isBlank()) {
            cover.append("<p class=\"meta\">").append(escapeHtml(options.date())).append("</p>");
        }
        cover.append("</div></div></section>");
        return cover.toString();
    }

    private String buildToc(List<Path> chapters) {
        StringBuilder toc = new StringBuilder();
        toc.append("<section class=\"toc\"><h2>Contents</h2><ol>");
        int idx = 1;
        for (Path file : chapters) {
            String id = "chapter-" + idx;
            String heading = escapeHtml(stripExtension(file.getFileName().toString()));
            toc.append("<li><a href=\"#").append(id).append("\">")
                    .append(heading)
                    .append("</a><span class=\"toc-page\" data-ref=\"#")
                    .append(id)
                    .append("\"></span></li>");
            idx++;
        }
        toc.append("</ol></section>");
        return toc.toString();
    }

    private String cssForTheme(BuildOptions.Theme theme) {
        return switch (theme) {
            case SANS_LIGHT -> "body{font-family:'Helvetica Neue','Arial',sans-serif;background:#f8fafc;color:#1f2937;}"
                    + "h1,h2,h3{color:#0f172a;}";
            case DARK -> "body{font-family:'Inter','Segoe UI',sans-serif;background:#0f172a;color:#e2e8f0;}"
                    + "h1,h2,h3{color:#e2e8f0;}.chapter{background:#111827;border-radius:10px;padding:1.5rem;}"
                    + ".book-header{border-color:#1f2937;}"
                    + ".toc{background:#0b1221;}"
                    + "code{background:#1f2937;color:#e2e8f0;border:1px solid #2b364a;}"
                    + "pre code{background:#0b1221;color:#e5e7eb;border:1px solid #2b364a;}";
            case SERIF_LIGHT -> "body{font-family:'Georgia','Times New Roman',serif;background:#fdfcf9;color:#222;}"
                    + "h1,h2,h3{color:#0c3a5b;}";
        };
    }

    private String baseCss() {
        return ".book-header{border-bottom:2px solid #0c3a5b;padding-bottom:0.6em;margin-bottom:1.2em;}"
                + ".meta{color:#666;font-size:0.9em;}"
                + ".chapter{page-break-before:always;margin-top:2em;}"
                + ".chapter h2{border-bottom:1px solid #d9e3ec;padding-bottom:0.3em;}"
                + "p{line-height:1.5em;margin:0 0 0.8em 0;}"
                + "code{background:#f5f7f9;padding:0.12em 0.3em;border-radius:4px;font-family:'JetBrains Mono','Consolas',monospace;}"
                + "pre code{display:block;padding:1em;}"
                + "ul,ol{margin:0 0 0.8em 1.3em;}"
                + ".cover{page-break-after:always;text-align:center;padding:3rem 1rem;background:#f5f7fb;border-radius:12px;}"
                + ".cover-content{display:block;margin:0 auto;max-width:640px;}"
                + ".cover-image{width:280px;height:320px;margin:0 auto 1rem auto;background-size:cover;background-position:center;border-radius:14px;}"
                + ".cover-text h1{font-size:2.4rem;margin:0 0 0.4em 0;}"
                + ".cover-text .meta{font-size:1rem;color:#555;}"
                + ".toc{page-break-after:always;padding:1rem 1.2rem;background:#f5f7fb;border-radius:12px;border:1px solid #e0e7f1;}"
                + ".toc h2{margin-top:0;}"
                + ".toc ol{list-style:none;padding:0;}"
                + ".toc li{display:block;border-bottom:1px dotted #cfd8e3;padding:0.35rem 0;}"
                + ".toc li .toc-page{float:right;}"
                + ".toc a{text-decoration:none;color:#0c3a5b;}"
                + ".toc-page::after{content: leader('.') target-counter(attr(data-ref), page);}";
    }
}
