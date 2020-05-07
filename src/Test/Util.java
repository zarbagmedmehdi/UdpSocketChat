package Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Util {

    public static boolean containsId(final List<ClientModel> list, final String id){
        return list.stream().filter(o -> o.getId().equals(id)).findFirst().isPresent();
    }
    public static boolean containsName(final List<ClientModel> list, final String name){
        return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
    }
    public static Optional<ClientModel> getClientModelByName(final List<ClientModel> list, final String name){

        return list.stream().filter(o -> o.getName().equals(name)).findFirst();
    }
    public static int getIndexOfList(List<ClientModel> list,String id){
        int index=0;
        for (int i=0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
                index=i;
        }
        return index;
    }
    public static Object[] getObjectOfList(List<ClientModel> list,String name){
        int index=0;
        ClientModel cl=null;
        for (int i=0; i < list.size(); i++) {
            if(list.get(i).getName().equals(name))
                index=i;
            cl=list.get(i);

        }
        return new Object[] {index,cl};
    }

    public static   int getMessageType(String data){
        return Integer.parseInt(data.substring(0,1));
    }
    public static   String getStringFrom(String  data){
        return data.split("/")[1] ;
    }

    public static String t(){
        Date d1 = new Date();
       return d1.getHours()+":"+d1.getSeconds()+"   ";

    }
}
