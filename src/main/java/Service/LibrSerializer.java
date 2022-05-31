package Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Library;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;

public class LibrSerializer {
    private static final File file = new File("src/main/resources/library.json");

    private ObjectMapper mapper;

    public LibrSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Library getLib() throws IOException {
        try {
            file.createNewFile();
            return mapper.readValue(file, Library.class);
        } catch (IIOException e) {
            return new Library();
        }
    }
    public void saveLib(Library library){
        try {
            mapper.writeValue(file,library);

        }catch (IIOException e){
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
