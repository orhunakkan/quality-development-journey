package com.example.bookmaker;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuildOptions {
    public enum Theme {
        SERIF_LIGHT,
        SANS_LIGHT,
        DARK
    }

    private final List<Path> chapters;
    private final String title;
    private final String author;
    private final String date;
    private final Path coverImage;
    private final boolean includeToc;
    private final Theme theme;
    private final Path baseFolder;

    private BuildOptions(Builder builder) {
        this.chapters = List.copyOf(builder.chapters);
        this.title = builder.title;
        this.author = builder.author;
        this.date = builder.date;
        this.coverImage = builder.coverImage;
        this.includeToc = builder.includeToc;
        this.theme = builder.theme;
        this.baseFolder = builder.baseFolder;
    }

    public List<Path> chapters() {
        return chapters;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String date() {
        return date;
    }

    public Path coverImage() {
        return coverImage;
    }

    public boolean includeToc() {
        return includeToc;
    }

    public Theme theme() {
        return theme;
    }

    public Path baseFolder() {
        return baseFolder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Path> chapters = new ArrayList<>();
        private String title = "Markdown Book";
        private String author = "";
        private String date = LocalDate.now().toString();
        private Path coverImage;
        private boolean includeToc = true;
        private Theme theme = Theme.SERIF_LIGHT;
        private Path baseFolder;

        public Builder chapters(List<Path> chapters) {
            this.chapters = Objects.requireNonNullElseGet(chapters, ArrayList::new);
            return this;
        }

        public Builder title(String title) {
            if (title != null && !title.isBlank()) {
                this.title = title;
            }
            return this;
        }

        public Builder author(String author) {
            this.author = author != null ? author : "";
            return this;
        }

        public Builder date(String date) {
            if (date != null && !date.isBlank()) {
                this.date = date;
            }
            return this;
        }

        public Builder coverImage(Path coverImage) {
            this.coverImage = coverImage;
            return this;
        }

        public Builder includeToc(boolean includeToc) {
            this.includeToc = includeToc;
            return this;
        }

        public Builder theme(Theme theme) {
            if (theme != null) {
                this.theme = theme;
            }
            return this;
        }

        public Builder baseFolder(Path baseFolder) {
            this.baseFolder = baseFolder;
            return this;
        }

        public BuildOptions build() {
            return new BuildOptions(this);
        }
    }
}
