package rs.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

class Util {
    static byte[] toJson(Object object) throws IOException {
        return new ObjectMapper().writeValueAsBytes(object);
    }
}
