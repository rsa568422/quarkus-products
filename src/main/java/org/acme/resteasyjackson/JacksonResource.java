package org.acme.resteasyjackson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/resteasy-jackson/quarks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonResource {

    private final Set<Quark> quarks = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public JacksonResource() {
        quarks.add(new Quark("Up", ""));
        quarks.add(new Quark("Strange", ""));
        quarks.add(new Quark("Charm", ""));
        quarks.add(new Quark("???", null));
    }

    @GET
    public Set<Quark> list() {
        return quarks;
    }

    @POST
    public Set<Quark> add(Quark quark) {
        quarks.add(quark);
        return quarks;
    }

    @DELETE
    public Set<Quark> delete(Quark quark) {
        quarks.removeIf(existingQuark -> existingQuark.name.contentEquals(quark.name));
        return quarks;
    }

    public static class Quark {

        public String name;
        public String description;

        public Quark() {

        }

        public Quark(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }
}