package REST.beans;
/**
 * Created by civi on 26/04/16.
 */
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class WordsDictionary {

    @XmlElement(name="my_words")
    private List<Word> wordlist;

    private static WordsDictionary instance;

    private WordsDictionary() {
        wordlist = new ArrayList<Word>();
    }

    //singleton
    public synchronized static WordsDictionary getInstance(){
        if(instance==null)
            instance = new WordsDictionary();
        return instance;
    }

    public synchronized List<Word> getWordList() {

        return new ArrayList<>(wordlist);

    }

    public synchronized void setWordlist(List<Word> wordlist) {
        this.wordlist = wordlist;
    }

    public synchronized void add(Word w){
        wordlist.add(w);
    }

    public synchronized void change(Word w) {
        for(Word wr: getWordList()){
            if(wr.getWord().equalsIgnoreCase(w.getWord()))
                wr.setDefinition(w.getDefinition());
        }
    }

    public Word getByWord(String word){
        List<Word> wordCopy = getWordList();
        for(Word w: wordCopy)
            if(w.getWord().toLowerCase().equals(word.toLowerCase()))
                return w;
        return null;
    }

    public String getDefinitionByWord(String word){
        List<Word> wordCopy = getWordList();
        for(Word w: wordCopy)
            if(w.getWord().equalsIgnoreCase(word))
                return w.getDefinition();
        return null;
    }

    public synchronized void delete(String word){
        for(Word wr: getWordList()) {
            if (wr.getWord().equalsIgnoreCase(word))
                wordlist.remove(wr);
        }
    }

}