@echo off
rem java -version

set API_CP=.;./*;./ext/*;./api/*;./tuto/*


rem -------------------------
rem Tuto
rem --------------------------

rem ---------
rem client
rem ---------

rem java -cp %API_CP% -Xmx500m -Xms200m ch.hearc.freescale.use.tuto.client.console.UseTutoClientConsole

rem java -cp  %API_CP% -Xmx500m -Xms200m ch.hearc.freescale.use.tuto.client.gui.UseTutoClientGUI

rem java -cp  %API_CP% -Xmx500m -Xms200m ch.hearc.freescale.use.tuto.client.gui.custom.UseTutoClientCustomGUI

rem ---------
rem server
rem ---------

rem java -cp  %API_CP% -Xmx500m -Xms200m ch.hearc.freescale.use.tuto.server.simulator.UseTutoServerSimulator

rem -------------------------
rem Me
rem --------------------------

java -cp %API_CP% -Xmx500m -Xms200m ch.hearc.freescale.use.Use

pause