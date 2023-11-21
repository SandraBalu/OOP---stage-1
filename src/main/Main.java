package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;

import commands.*;
import fileio.input.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    static final String LIBRARY_PATH = CheckerConstants.TESTS_PATH + "library/library.json";

    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().startsWith("library")) {
                continue;
            }

            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePathInput for input file
     * @param filePathOutput for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePathInput,
                              final String filePathOutput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryInput library = objectMapper.readValue(new File(LIBRARY_PATH), LibraryInput.class);

        ArrayNode outputs = objectMapper.createArrayNode();

        // TODO add your implementation
        String file_path = CheckerConstants.TESTS_PATH + filePathInput;
        ObjectMapper objectMapper1 = new ObjectMapper();

        ProcessCommands processCommands = new ProcessCommands();
        ArrayList<String> matching = new ArrayList<>();

        List<JsonNode> jsonNodeList = objectMapper1.readValue(new File(file_path), objectMapper1.getTypeFactory().constructCollectionType(List.class, JsonNode.class));

        for (JsonNode jsonNode : jsonNodeList) {
            TypeFactory typeFactory = objectMapper1.getTypeFactory();

            switch (jsonNode.get("command").textValue()) {
                case "createPlaylist":
                    CreatePlaylistCommand createPlaylistCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(CreatePlaylistCommand.class));

                    break;
                case "search":
                    SearchCommand searchCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(SearchCommand.class));
                    matching = processCommands.ExecuteSearch(searchCommand, library);

                    ObjectNode matchingResults = objectMapper.createObjectNode();
                    matchingResults.put("command", "search");
                    matchingResults.put("user", searchCommand.getUsername());
                    matchingResults.put("timestamp",searchCommand.getTimestamp());
                    matchingResults.put("message", "Search returned " +  matching.size() + " results");

                    ArrayNode matchingArrayNode = matchingResults.putArray("results");
                    for (String song : matching) {
                        matchingArrayNode.add(song);
                    }
                    outputs.add(matchingResults);
                    break;
                case "load":
                    LoadCommand loadCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(LoadCommand.class));
                    break;
                case "select":
                    SelectCommand selectCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(SelectCommand.class));
                    String select = processCommands.ExecuteSelect(matching, selectCommand);
                    ObjectNode selectedResult = objectMapper.createObjectNode();
                    selectedResult.put("command", "select");
                    selectedResult.put("user", selectCommand.getUsername());
                    selectedResult.put("timestamp",selectCommand.getTimestamp());
                    selectedResult.put("message", select);
                    outputs.add(selectedResult);

                    break;
                case "repeat":
                    RepeatCommand repeatCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(RepeatCommand.class));
                    break;
                case "shuffle":
                    ShuffleCommand shuffleCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(ShuffleCommand.class));
                    break;
                case "forward":
                    ForwardCommand forwardCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(ForwardCommand.class));
                case "backward":
                    BackwardCommand backwardCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(BackwardCommand.class));
                    break;
                case "like":
                    LikeCommand likeCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(LikeCommand.class));
                    break;
                case "next":
                    NextCommand nextCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(NextCommand.class));
                    break;
                case "prev":
                    PrevCommand prevCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(PrevCommand.class));
                    break;
                case "addRemoveInPlaylist":
                    AddRemoveInPlaylistCommand addRemoveInPlaylistCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(AddRemoveInPlaylistCommand.class));
                    break;
                case "status":
                    StatusCommand statusCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(StatusCommand.class));
                    break;
                case "switchVisibility":
                    SwitchVisibilityCommand switchVisibilityCommandw = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(SwitchVisibilityCommand.class));
                    break;
                case "followPlaylist":
                    FollowPlaylistCommand followPlaylistCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(FollowPlaylistCommand.class));
                    break;
                case "showPlaylist":
                    ShowPlaylistCommand showPlaylistCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(ShowPlaylistCommand.class));
                    break;
                case "showPreferredSong":
                    ShowPreferredSongsCommand showPreferredSongsCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(ShowPreferredSongsCommand.class));
                    break;
                case "getTop5Songs":
                    GetTop5SongsCommand getTop5SongsCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(GetTop5SongsCommand.class));
                    break;
                case "getTop5Playlists":
                    GetTop5PlaylistsCommand getTop5PlaylistsCommand = objectMapper1.treeToValue(jsonNode, typeFactory.constructType(GetTop5PlaylistsCommand.class));
                    break;
                default:
                    break;
            }
        }


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePathOutput), outputs);
    }

}

