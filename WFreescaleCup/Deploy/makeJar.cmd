@echo off

set PATH_7Z=C:\Soft\sevenZip
set PATH=%PATH%;%PATH_7z%

7z a -tzip Deploy/tuto/PUse_Tuto_ClientConsole.jar ../PUse_Tuto_ClientConsole/bin/ch
7z a -tzip Deploy/tuto/PUse_Tuto_ClientGUI.jar ../PUse_Tuto_ClientGUI/bin/ch
7z a -tzip Deploy/tuto/PUse_Tuto_CustomGUI.jar ../PUse_Tuto_CustomGUI/bin/ch
7z a -tzip Deploy/tuto/PUse_Tuto_ServerSimulator.jar ../PUse_Tuto_ServerSimulator/bin/ch

7z a -tzip Deploy/PUse.jar ../PUse/bin/ch

pause