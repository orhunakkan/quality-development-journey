package com.example.bookmaker;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookMakerApp extends Application {

    private final MarkdownBookBuilder builder = new MarkdownBookBuilder();
    private final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private TextField folderField;
    private TextField outputField;
    private TextField titleField;
    private TextField authorField;
    private TextField dateField;
    private TextField coverField;
    private CheckBox tocCheck;
    private ChoiceBox<BuildOptions.Theme> themeChoice;
    private ListView<ChapterItem> filesList;
    private ObservableList<ChapterItem> chapterItems = FXCollections.observableArrayList();
    private TextArea logArea;
    private Button buildButton;
    private Label filesInfoLabel;

    private Path selectedFolder;
    private Path outputFile;
    private Path coverImage;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Markdown Book Maker");

        BorderPane root = new BorderPane();
        root.setTop(buildHeader());
        root.setCenter(buildMainContent(stage));
        root.setBottom(buildFooter());

        Scene scene = new Scene(root, 900, 640);
        stage.setScene(scene);
        stage.show();
    }

    private VBox buildHeader() {
        Label title = new Label("Markdown Book Maker");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label subtitle = new Label("Pick a folder with .md files and export them as a single PDF book.");
        subtitle.setStyle("-fx-text-fill: #444;");

        VBox header = new VBox(4, title, subtitle);
        header.setPadding(new Insets(16, 18, 10, 18));
        return header;
    }

    private VBox buildMainContent(Stage stage) {
        folderField = new TextField();
        folderField.setPromptText("Folder containing markdown files");
        folderField.setEditable(false);

        outputField = new TextField();
        outputField.setPromptText("Where should the PDF be saved?");

        Button browseFolder = new Button("Choose folder");
        browseFolder.setOnAction(event -> chooseFolder(stage));

        Button browseOutput = new Button("Choose file");
        browseOutput.setOnAction(event -> chooseOutputFile(stage));

        titleField = new TextField();
        titleField.setPromptText("Book title");

        authorField = new TextField();
        authorField.setPromptText("Author (optional)");

        dateField = new TextField(LocalDateTime.now().toLocalDate().toString());
        dateField.setPromptText("Date (defaults to today)");

        coverField = new TextField();
        coverField.setPromptText("Cover image (png/jpg)");
        coverField.setEditable(false);
        Button coverButton = new Button("Choose cover");
        coverButton.setOnAction(event -> chooseCover(stage));

        tocCheck = new CheckBox("Include table of contents");
        tocCheck.setSelected(true);

        themeChoice = new ChoiceBox<>(FXCollections.observableArrayList(BuildOptions.Theme.values()));
        themeChoice.getSelectionModel().select(BuildOptions.Theme.SERIF_LIGHT);

        filesList = new ListView<>(chapterItems);
        filesList.setPlaceholder(new Label("Pick a folder to list markdown files"));
        filesList.setPrefHeight(260);
        filesList.setCellFactory(chapterCellFactory());
        filesInfoLabel = new Label("No folder selected");

        buildButton = new Button("Create Book");
        buildButton.setPrefWidth(140);
        buildButton.setOnAction(event -> runBuild());

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(10);
        form.setPadding(new Insets(10, 18, 12, 18));

        form.add(new Label("Markdown folder"), 0, 0);
        form.add(folderField, 1, 0);
        form.add(browseFolder, 2, 0);

        form.add(new Label("Output PDF"), 0, 1);
        form.add(outputField, 1, 1);
        form.add(browseOutput, 2, 1);

        form.add(new Label("Title"), 0, 2);
        form.add(titleField, 1, 2);
        form.add(new Label("Theme"), 2, 2);
        form.add(themeChoice, 3, 2);

        form.add(new Label("Author"), 0, 3);
        form.add(authorField, 1, 3);
        form.add(tocCheck, 2, 3, 2, 1);

        form.add(new Label("Date"), 0, 4);
        form.add(dateField, 1, 4);

        form.add(new Label("Cover image"), 0, 5);
        form.add(coverField, 1, 5);
        form.add(coverButton, 2, 5);

        HBox actionRow = new HBox(buildButton);
        actionRow.setAlignment(Pos.BASELINE_RIGHT);

        VBox center = new VBox(10);
        center.setPadding(new Insets(6, 18, 10, 18));
        center.getChildren().addAll(form, filesInfoLabel, filesList, actionRow);
        VBox.setMargin(actionRow, new Insets(4, 0, 0, 0));

        return center;
    }

    private VBox buildFooter() {
        Label logLabel = new Label("Activity");

        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefRowCount(6);
        logArea.setWrapText(true);

        VBox footer = new VBox(6, logLabel, logArea);
        footer.setPadding(new Insets(6, 18, 14, 18));
        return footer;
    }

    private void chooseFolder(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Pick folder with Markdown files");
        if (selectedFolder != null && Files.isDirectory(selectedFolder)) {
            chooser.setInitialDirectory(selectedFolder.toFile());
        }

        File chosen = chooser.showDialog(stage);
        if (chosen != null) {
            selectedFolder = chosen.toPath();
            folderField.setText(selectedFolder.toString());
            defaultOutputFile();
            refreshMarkdownList();
            appendLog("Selected folder: " + selectedFolder);
        }
    }

    private void chooseOutputFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save book as");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files", "*.pdf"));

        if (outputFile != null) {
            chooser.setInitialFileName(outputFile.getFileName().toString());
            Path parent = outputFile.getParent();
            if (parent != null && Files.isDirectory(parent)) {
                chooser.setInitialDirectory(parent.toFile());
            }
        } else if (selectedFolder != null && Files.isDirectory(selectedFolder)) {
            chooser.setInitialDirectory(selectedFolder.toFile());
            chooser.setInitialFileName("book.pdf");
        }

        File selected = chooser.showSaveDialog(stage);
        if (selected != null) {
            outputFile = selected.toPath();
            outputField.setText(outputFile.toString());
            appendLog("Output file set to: " + outputFile);
        }
    }

    private void chooseCover(Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose cover image");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg", "*.webp"));
        File selected = chooser.showOpenDialog(stage);
        if (selected != null) {
            coverImage = selected.toPath();
            coverField.setText(coverImage.toString());
            appendLog("Cover set to: " + coverImage);
        }
    }

    private void refreshMarkdownList() {
        chapterItems.clear();
        if (selectedFolder == null) {
            filesInfoLabel.setText("No folder selected");
            return;
        }

        try {
            List<Path> files = builder.listMarkdownFiles(selectedFolder);
            if (files.isEmpty()) {
                filesInfoLabel.setText("No .md files found in folder");
            } else {
                filesInfoLabel.setText("Found " + files.size() + " markdown file(s)");
                files.forEach(f -> chapterItems.add(new ChapterItem(f)));
                if (titleField.getText().isBlank()) {
                    titleField.setText(selectedFolder.getFileName().toString());
                }
            }
        } catch (Exception ex) {
            filesInfoLabel.setText("Failed to read folder");
            appendLog("Error reading folder: " + ex.getMessage());
        }
    }

    private void runBuild() {
        if (selectedFolder == null) {
            showAlert("Choose a folder first", "Please select the folder that holds your markdown files.");
            return;
        }

        String outputText = outputField.getText().trim();
        if (outputText.isEmpty()) {
            defaultOutputFile();
            outputText = outputField.getText().trim();
        }

        if (outputText.isEmpty()) {
            showAlert("Choose where to save the PDF", "Please select an output file for the book.");
            return;
        }

        outputFile = Path.of(outputText);
        List<Path> selected = chapterItems.stream()
                .filter(ChapterItem::isIncluded)
                .map(ChapterItem::path)
                .collect(Collectors.toList());
        if (selected.isEmpty()) {
            showAlert("Select chapters", "Please include at least one markdown file.");
            return;
        }

        BuildOptions options = BuildOptions.builder()
                .chapters(selected)
                .title(titleField.getText())
                .author(authorField.getText())
                .date(dateField.getText())
                .coverImage(coverImage)
                .includeToc(tocCheck.isSelected())
                .theme(themeChoice.getValue())
                .baseFolder(selectedFolder)
                .build();

        appendLog("Starting build...");
        setUiDisabled(true);

        Task<Path> task = new Task<>() {
            @Override
            protected Path call() throws Exception {
                return builder.buildBook(options, outputFile, this::updateMessage);
            }
        };

        task.messageProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.isBlank()) {
                appendLog(newValue);
            }
        });

        task.setOnSucceeded(event -> {
            appendLog("Book created: " + task.getValue());
            setUiDisabled(false);
        });

        task.setOnFailed(event -> {
            Throwable error = task.getException();
            showAlert("Failed to create book", error != null ? error.getMessage() : "Unknown error");
            appendLog("Error: " + (error != null ? error.getMessage() : "Unknown"));
            setUiDisabled(false);
        });

        Thread worker = new Thread(task);
        worker.setDaemon(true);
        worker.start();
    }

    private void defaultOutputFile() {
        if (selectedFolder != null) {
            outputFile = selectedFolder.resolve("book.pdf");
            outputField.setText(outputFile.toString());
        }
    }

    private void setUiDisabled(boolean disable) {
        buildButton.setDisable(disable);
    }

    private void appendLog(String message) {
        String time = timestampFormatter.format(LocalDateTime.now());
        logArea.appendText("[" + time + "] " + message + System.lineSeparator());
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private Callback<ListView<ChapterItem>, javafx.scene.control.ListCell<ChapterItem>> chapterCellFactory() {
        return list -> new javafx.scene.control.ListCell<>() {
            private final CheckBox checkBox = new CheckBox();
            private final Label nameLabel = new Label();
            private final HBox box = new HBox(8, checkBox, nameLabel);

            {
                box.setAlignment(Pos.CENTER_LEFT);
            }

            @Override
            protected void updateItem(ChapterItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    nameLabel.setText(item.path().getFileName().toString());
                    checkBox.selectedProperty().unbind();
                    checkBox.setSelected(item.isIncluded());
                    checkBox.selectedProperty().bindBidirectional(item.includedProperty());
                    setGraphic(box);
                }
            }
        };
    }

    private static class ChapterItem {
        private final Path path;
        private final BooleanProperty included = new SimpleBooleanProperty(true);

        ChapterItem(Path path) {
            this.path = path;
        }

        Path path() {
            return path;
        }

        BooleanProperty includedProperty() {
            return included;
        }

        boolean isIncluded() {
            return included.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
