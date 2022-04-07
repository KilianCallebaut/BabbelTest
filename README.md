# BabbelTest

## Time investment

In total this exercise took about 4 hours to implement. 

## Time Distribution

It took about 15 minutes to draw out the concept and get an overview of all elements and mechanics to implement. 
After that the 3 views were implemented one by one which took about an hour and a half. The game logic was implemented in about an hour and a half.
The finally, the result screen was made and the bugs were fixed in the last hour.

## Decisions

For one, the main decision was made to implement this as a series of 7 words, which are evaluated after each session. 
I wanted to keep in mind that the (theoretical) goal is to actually learn from this excercise in stead of quickly raking up points.
These results can then be checked with the correct answer, giving a red color if the user answered wrong or a green color if they did it right.
These are done in series of 7 because of Miller's magic number in human memory capacity, with the goal of the excercise being more reflective.

The falling words themselves edge toward a danger zone at which point the word disappears and the user hasn't responded in time.
In order to effectively communicate how long they still have left a timer is implemented in the top left corner as well.
In the top right there is a progress bar showing how much of the 7 words are still left.

## Cut for time

I stayed pretty basic in terms of design, just trying to effectively implement the elements on screen. 
Originally I wanted to make the words swipeable, but in stead opted for buttons to start and eventually stuck with them.
Testing is pretty limited, the fragment structure I used turned out to be more complicated to easily perform integration testing.
I wanted to go more in depth in providing more structure as to which 7 words were pulled, but that would require the implementation of grouping structures for the words which I didn't see as worth it for the time given.

## First thing to improve

More visual flair would be nice, as well as some overarching progress structure so that they're not just random series of 7 words every time.
The testing structure should be implemented better. Or the structure changed for easier integration.
Perhaps switching between english and spanish should be an option.
