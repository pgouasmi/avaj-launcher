all: compile exec

compile:
	@echo "Compiling the source code..."
	@find * -name "*.java" > sources.txt
	@javac @sources.txt
	@echo "Source code compiled\n"

exec:
	@echo "lauching exec...\n"
	@java -cp src avaj.simulator.Simulator scenario.txt

clean:
	@rm -f src/avaj/*/*.class
	@rm -f sources.txt
	@rm -f simulation.txt

re: clean all

