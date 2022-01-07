import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Serializable {
    void load(File file) throws FileNotFoundException;

    void save(File file) throws IOException;
}
