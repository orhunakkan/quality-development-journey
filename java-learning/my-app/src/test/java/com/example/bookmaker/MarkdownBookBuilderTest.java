package com.example.bookmaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

public class MarkdownBookBuilderTest {

    @Test
    public void listsMarkdownFiles_sortedAndFiltered() throws Exception {
        Path temp = Files.createTempDirectory("md-list");
        Path b = write(temp.resolve("b-notes.md"), "# B");
        write(temp.resolve("a-intro.MD"), "# A"); // uppercase extension should count
        write(temp.resolve("ignore.txt"), "not md");

        MarkdownBookBuilder builder = new MarkdownBookBuilder();
        List<Path> files = builder.listMarkdownFiles(temp);

        assertEquals("Should only include markdown files", 2, files.size());
        assertEquals("Files should be sorted by name", List.of(temp.resolve("a-intro.MD"), b), files);
    }

    @Test
    public void buildBook_outputsPdfFile() throws Exception {
        Path temp = Files.createTempDirectory("md-build");
        write(temp.resolve("01-intro.md"), "# Intro\n\nHello book!");
        write(temp.resolve("02-body.md"), "# Body\n\nSecond chapter.");

        Path output = temp.resolve("book.pdf");
        MarkdownBookBuilder builder = new MarkdownBookBuilder();

        List<String> progress = new CopyOnWriteArrayList<>();
        BuildOptions options = BuildOptions.builder()
                .chapters(builder.listMarkdownFiles(temp))
                .title("Test Book")
                .author("Tester")
                .baseFolder(temp)
                .build();
        Path result = builder.buildBook(options, output, progress::add);

        assertTrue("Progress should have reported steps", progress.size() >= 2);
        assertTrue("PDF file should exist", Files.exists(result));
        assertTrue("PDF should not be empty", Files.size(result) > 100);
    }

    private Path write(Path target, String content) throws IOException {
        Files.writeString(target, content, StandardCharsets.UTF_8);
        return target;
    }
}
