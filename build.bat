::@echo off
javac Game.java View.java Controller.java Pipe.java Mario.java Goomba.java Fireball.java
if %errorlevel% neq 0 (
	echo There was an error; exiting now.	
) else (
	echo Compiled correctly!  Running Game...
	java Game	
)
