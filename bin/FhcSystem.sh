#!/bin/sh
SERVICE_NAME=FhcSystemService
PATH_TO_JAR=../src/Main.jar
PID_PATH_NAME=/tmp/FhcSystemService-pid

# Colors :3
NC='\033[0m'       # Text Reset
# Regular Colors
Black='\033[0;30m'        # Black
Red='\033[0;31m'          # Red
Green='\033[0;32m'        # Green
Yellow='\033[0;33m'       # Yellow
Blue='\033[0;34m'         # Blue
Purple='\033[0;35m'       # Purple
Cyan='\033[0;36m'         # Cyan
White='\033[0;37m'        # White

case $1 in
	start)
		echo "${Green}
        ███████╗██╗  ██╗ ██████╗    ███████╗██╗   ██╗███████╗████████╗███████╗███╗   ███╗
        ██╔════╝██║  ██║██╔════╝    ██╔════╝╚██╗ ██╔╝██╔════╝╚══██╔══╝██╔════╝████╗ ████║
        █████╗  ███████║██║         ███████╗ ╚████╔╝ ███████╗   ██║   █████╗  ██╔████╔██║
        ██╔══╝  ██╔══██║██║         ╚════██║  ╚██╔╝  ╚════██║   ██║   ██╔══╝  ██║╚██╔╝██║
        ██║     ██║  ██║╚██████╗    ███████║   ██║   ███████║   ██║   ███████╗██║ ╚═╝ ██║
        ╚═╝     ╚═╝  ╚═╝ ╚═════╝    ╚══════╝   ╚═╝   ╚══════╝   ╚═╝   ╚══════╝╚═╝     ╚═╝${NC}

                        ${Yellow}Forecasting Householding Consumption System${NC}
                                   ${Yellow} Version Beta 1.0${NC}

                                 A software by: PowerOne \n\n"
		
		echo "Starting the Forecasting Householding Consumption System..."
		if [ ! -f $PID_PATH_NAME ]; then
			nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
			echo $! > $PID_PATH_NAME
			echo "$SERVICE_NAME is started..."
		else
			echo "$SERVICE_NAME is already running..."
		fi
	;;
	stop)
		if [ -f $PID_PATH_NAME ]; then
			PID=$(cat $PID_PATH_NAME);
			echo "Stoping the Forecasting Householding Consumption System..."
			echo "$SERVICE_NAME Stopping..."
			kill $PID;
			echo "$SERVICE_NAME stopped..."
			echo "Come back soon!"
			rm $PID_PATH_NAME
		else
			echo "$SERVICE_NAME is not running..."
		fi
	;;
	restart)
		if [ -f $PID_PATH_NAME ]; then
			echo "Restarting the Forecasting Householding Consumption System..."
			PID=$(cat $PID_PATH_NAME);
			echo "$SERVICE_NAME stopping..."
			kill $PID;
			echo "$SERVICE_NAME stopped..."
			rm $PID_PATH_NAME
			echo "$SERVICE_NAME starting..."
			nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
			echo $! > $PID_PATH_NAME
			echo "$SERVICE_NAME is started..."
		else
			echo "$SERVICE_NAME is not running..."
		fi
	;;
esac