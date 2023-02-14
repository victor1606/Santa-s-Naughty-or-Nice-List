package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import input.SimulationData;

import java.io.File;
import java.io.IOException;

public final class InputReader {

    /**
     * This method reads all JSON data from an input test file
     * and maps it to a SimulationData object.
     *
     * @param inFile contains the name of the input file
     */
    public static SimulationData readData(final File inFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inFile, SimulationData.class);
    }

    private InputReader() {
    }
}
