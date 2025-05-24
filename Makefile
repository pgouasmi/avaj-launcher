GREEN := \033[0;32m
YELLOW := \033[0;33m
RED := \033[0;31m
NC := \033[0m

all: compile exec

compile:
	@echo "${YELLOW}Compiling the source code...${NC}"
	find * -name "*.java" > sources.txt
	javac @sources.txt
	@echo "${GREEN}Source code compiled!\n${NC}"

exec:
	@echo "${YELLOW}Executing...\n${NC}"
	java -cp src avaj.simulator.Simulator scenario.txt
	@echo "${GREEN}\nSimulation result is written in scenario.txt!"

clean:
	@echo "${YELLOW}Cleaning the temporary files and .class files...${NC}"
	rm -f src/avaj/*/*.class
	rm -f sources.txt
	rm -f simulation.txt
	@echo "${GREEN}Repository cleaned up!\n${NC}"

re: clean all

