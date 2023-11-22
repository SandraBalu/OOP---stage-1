package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
        String filePath = CheckerConstants.TESTS_PATH + filePathInput;
        ObjectMapper objectMapper1 = new ObjectMapper();
        ArrayList<SongInput> matchingSongs = new ArrayList<>();
        ArrayList<PodcastInput> matchingPodcasts = new ArrayList<>();

        Current current = new Current();
        SongInput currentSong = new SongInput();
        PodcastInput currentPodcast = new PodcastInput();
        int timestampAnt = 0; String antCommandName = null;
        // 0 - no search, 1 - search song, 2 - search podcast

        List<JsonNode> jsonNodeList = objectMapper1.readValue(new File(filePath),
                objectMapper1.getTypeFactory().constructCollectionType(List.class,
                        JsonNode.class));

        for (JsonNode jsonNode : jsonNodeList) {
            TypeFactory typeFactory = objectMapper1.getTypeFactory();

            switch (jsonNode.get("command").textValue()) {
                case "createPlaylist":
                    CreatePlaylistCommand createPlaylist = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(CreatePlaylistCommand.class));

                    break;
                case "search":
                    SearchCommand searchCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(SearchCommand.class));
                    searchCommand.executeSearch(searchCommand, current, library, objectMapper, outputs);

                    break;
                case "load":
                    LoadCommand loadCommand = objectMapper1.treeToValue(jsonNode,
                                typeFactory.constructType(LoadCommand.class));
                    loadCommand.executeLoad(loadCommand,current, library, objectMapper, outputs);

                    break;
                case "select":
                    SelectCommand selectCommand = objectMapper1.treeToValue(jsonNode,
                                typeFactory.constructType(SelectCommand.class));
                    selectCommand.executeSelect(selectCommand, current, library, objectMapper, outputs);

                    break;

                case "playPause":
                    PlayPauseCommand playPause = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(PlayPauseCommand.class));
                    playPause.executePlayPause(playPause, current, library, objectMapper, outputs);

                    break;

                case "repeat":
                    RepeatCommand repeatCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(RepeatCommand.class));
                    break;
                case "shuffle":
                    ShuffleCommand shuffleCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(ShuffleCommand.class));
                    break;
                case "forward":
                    ForwardCommand forwardCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(ForwardCommand.class));
                case "backward":
                    BackwardCommand backwardCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(BackwardCommand.class));
                    break;
                case "like":
                    LikeCommand likeCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(LikeCommand.class));
                    break;
                case "next":
                    NextCommand nextCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(NextCommand.class));
                    break;
                case "prev":
                    PrevCommand prevCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(PrevCommand.class));
                    break;
                case "addRemoveInPlaylist":
                    AddRemoveInPlaylistCommand addRmInPlaylist = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(AddRemoveInPlaylistCommand.class));
                    break;
                case "status":
                    StatusCommand statusCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(StatusCommand.class));
                    statusCommand.displayStatus(statusCommand, current, objectMapper,outputs);
                    break;
                case "switchVisibility":
                    SwitchVisibilityCommand switchVisibility = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(SwitchVisibilityCommand.class));
                    break;
                case "followPlaylist":
                    FollowPlaylistCommand followPlaylist = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(FollowPlaylistCommand.class));
                    break;
                case "showPlaylist":
                    ShowPlaylistCommand showPlaylistCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(ShowPlaylistCommand.class));
                    break;
                case "showPreferredSong":
                    ShowPreferredSongsCommand preferredSongs = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(ShowPreferredSongsCommand.class));
                    break;
                case "getTop5Songs":
                    GetTop5SongsCommand getTop5SongsCommand = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(GetTop5SongsCommand.class));
                    break;
                case "getTop5Playlists":
                    GetTop5PlaylistsCommand getTop5Playlists = objectMapper1.treeToValue(jsonNode,
                            typeFactory.constructType(GetTop5PlaylistsCommand.class));
                    break;
                default:
                    break;
            }
        }


        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePathOutput), outputs);
    }

}

