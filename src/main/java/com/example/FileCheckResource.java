package com.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/file-check")
public class FileCheckResource {

    @Inject
    FileCheckJob fileCheckJob;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getFileCheckInfo() {
        // Chama o método checkFile do FileCheckJob para garantir que os dados estejam atualizados
        fileCheckJob.checkFile();

        // Obtém as informações do FileCheckJob
        int fileCount = fileCheckJob.getFileCount();
        long lastCheckedTime = fileCheckJob.getLastCheckedTime();

        return "Files found: " + fileCount + "\nLast checked: " + lastCheckedTime;
    }
}
