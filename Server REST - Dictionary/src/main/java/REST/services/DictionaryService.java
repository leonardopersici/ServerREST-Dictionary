package REST.services;


import REST.beans.Word;
import REST.beans.WordsDictionary;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("wordsdictionary")
public class DictionaryService {

    //restituisce la lista di parole
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getWordList(){
        return Response.ok(WordsDictionary.getInstance()).build();

    }

    //permette di inserire un termine (parola e definizione)
    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response addWord(Word w){
        WordsDictionary.getInstance().add(w);
        return Response.ok().build();
    }

    //permette di prelevare un termine dizionario con una determinata parola
    @Path("get/{word}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getByWord(@PathParam("word") String word){
        Word w = WordsDictionary.getInstance().getByWord(word);
        if(w!=null)
            return Response.ok(w).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    //cambiare la definizione di una word
    @Path("change")
    @POST
    @Consumes({"application/json", "application/xml"})
    public Response changeDefinition(Word w){
        WordsDictionary.getInstance().change(w);
        return Response.ok().build();
    }

    //permette di prelevare una definizione dal dizionario data una parola
    @Path("getdefinition/{word}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getDefinitionByWord(@PathParam("word") String word){
        String d = WordsDictionary.getInstance().getDefinitionByWord(word);
        if(d!=null)
            return Response.ok(d).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    //permette di rimuovere un termine (parola e definizione)
    @Path("delete/{word}")
    @GET
    @Consumes({"application/json", "application/xml"})
    public Response deleteWord(@PathParam("word") String word){
        WordsDictionary.getInstance().delete(word);
        return Response.ok().build();
    }

}