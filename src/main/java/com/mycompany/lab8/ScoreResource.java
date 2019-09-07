package com.mycompany.lab8;

import java.util.Arrays;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Hudson
 */
@Path("")
public class ScoreResource {

    @Context
    private UriInfo context;

    @GET
    @Path("scores")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readScore() {
        Score[] sc = Database.getInstance().getScores();
        Arrays.sort(sc);
        
        Score[] topScores;
        if (sc.length > 100) {
            topScores = new Score[100];
        } else {
            topScores = new Score[sc.length];
        } 
        for (int i = 0; i < topScores.length; i++) {
            topScores[i] = sc[i];
        }
        return Response.ok(topScores).build();
    }

    @POST
    @Path("scores")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createScore(Score score) {
        Score sc = new Score(score.getName(), score.getScore());
        Database.getInstance().addScore(sc);
        int re = sc.getScore();
        return Response.ok(re).build();
    }

    @POST
    @Path("scoreRank")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createRankInfo(Score score) {
        Score[] sc = Database.getInstance().getScores();
        int rank = 1;
        Arrays.sort(sc);

        for (int i = 0; i < sc.length; i++) {
            if (score.getScore() < sc[i].getScore()) {
                rank++;
            } else {
                break;
            }
        }

        return Response.ok(rank).build();
    }
}
