import java.util.ArrayList;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ignacio on 19/04/2017.
 */
public class EtakemonManager {
    private static EtakemonManager instance =null;
    final static Logger logger= Logger.getLogger(EtakemonManager.class);
    private static HashMap<String,Usuario> cache;
    public static EtakemonManager getInstance() {
        if(instance==null)
            instance=new EtakemonManager();
        return instance;
    }
    private EtakemonManager() {
        cache=new HashMap<String, Usuario>();
        logger.info("Se ha creado la cache");
    }

    public List<Usuario> listar() {
        List<Usuario>list=new ArrayList<Usuario>(cache.values());
        return list;
    }

    public Usuario getUser(String name) {
        return cache.get(name);
    }
    List<Etackemons> getEtackemons(String name){
        List<Etackemons>list=new ArrayList<Etackemons>();
        return cache.get(name).getMios();
    }

    public boolean addUser(Usuario obj) {
        logger.info("Se ha añadido un usuario a la cache");
        if(!cache.containsKey(obj.getName())){
            cache.put(obj.getName(),obj);
            return true;}
        else
            return false;

    }

    public boolean modificarUser (String name, Usuario obj) {
        logger.info("Se ha modificado un usuario en la cache");
        if(cache.get(name)!=null){
            cache.remove(name);
            cache.put(obj.getName(),obj);
            return true;
        }
        else {
            logger.error("No se ha encontrado ese User");
            return false;}


    }
    boolean addEtackemon(String name,Etackemons etackemon){
        if(cache.get(name)!=null){
            if(cache.get(name).getEtackemon(name)!=null){
                Usuario a=cache.get(name);
                a.addEtaackemon(etackemon);
                modificarUser(name,a);
            }
            return true;
        }
        else {
            logger.error("No se ha podido añadir ese etackemon");
            return false;}
    }



}
