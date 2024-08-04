package payloads;

public class workspacePayload {
    String requestBody = "{\"search\":\"\",\"pagination\":{\"page\":1,\"itemsPerPage\":25,\"sortBy\":[\"name\"],\"descending\":[false],\"rowsPerPage\":25}}";
    public String getRequestBody() {
        return requestBody;
    }
}
