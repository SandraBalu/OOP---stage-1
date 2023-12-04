#SPOTIFY - OOP PROJECT 2023

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1)

## General description of the project
    For this project, an application similar in functionality to Spotify is 
being implemented, simulating different actions made by users. These actions
will be simulated using some commands received in Json input files.
   To execute commands, a foundational command class is designed, comprising
three fundamental fields: commandName, commandTimeStamp, and commandUsername.
These fields are universal for all user commands. Subsequently, distinct 
classes are derived from the base class for each command type. These
specialized classes incorporate additional fields, as needed, and implement 
unique methods tailored to execute their respective commands.



## Commands implementation


* search : Determine the entity to be searched, whether it's a song, podcast, 
or playlist, and assign its type to the variable "current.WhatIsOn." This 
variable will later indicate the type of data being processed (1 for song, 2 
for podcast, 3 for playlist). Subsequently, generate an array of up to five 
elements (predefined in memory) representing the search results. Save these 
results in the current Current variable for subsequent processing.

* select : This command will only be executed if a search has been performed
beforehand, and the results have been loaded into the "current" variable in the
corresponding field, provided that the selection was made correctly.

* load : This command is executed only if a correct selection has been made
previously. If loading a song, it starts from the beginning. If loading a 
playlist, it, like a song, starts from the beginning. If loading a podcast, it
checks if it has been played before. If yes, it determines the last episode 
that was paused and the exact second it was paused at. If the current podcast 
has never been accessed, it is saved in an array of podcasts that retains data
about all previously loaded podcasts.

* createPlaylist : This command generates a playlist for the current user,
using the provided name as a parameter, only if no playlist with the specified
name already exists.

* like : This command specifically pertains to playing songs. When the current
song receive a first rating, they are appended to an array within the
ExtendedUser variable, which serves as a repository for liked sources. 
Nevertheless, if the command is executed for a playlist/song that already 
exists in the designated playlist or song list, it is removed due to the 
execution of the "unlike" command.

* addRemoveInPlaylist : This command is exclusively executed when a song is
actively playing. If such is the case, it verifies whether the song is already
present in the designated playlist. If the song is not found, it is included;
otherwise, it is removed.

* showPlaylists : This command browses the array of existing playlists and if 
they belong to the user who executed the command, they are displayed

* showPreferredSongs : This command scans the ExtendedUsers array for the 
username associated with the issuing command. Upon locating the username, it
displays the array containing the user's liked songs.

* repeat : This command sets the repeat mode for the current entity.
Essentially, it transitions from state 0 to state 1, then from 1 to 2, and 
finally from 2 back to 0. The actual state depends on the type of content
currently being played by the player. Consequently, both the container state 
and the repeat state are stored in a current variable.

* status : This command displays details about the current entity. Within this
command, the remaining time is also calculated based on the entity's state, 
taking into account both its type and repeat state.
