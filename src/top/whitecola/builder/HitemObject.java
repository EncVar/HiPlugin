package top.whitecola.builder;

public class HitemObject {
//    CraftPlayer
//    public static void writeHitemToFile(HiItem ht,File file) throws IOException{
//        ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream(file,false));
//        ops.writeObject(ht);
//        ops.flush();
//        ops.close();
//    }
//
//    public static HiItem readObjectToHiItem(File file) throws IOException,ClassNotFoundException {
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//        Object obj = ois.readObject();
//        ois.close();
//        if(obj instanceof HiItem){
//            return (HiItem)obj;
//        }
//        return null;
//
//    }
//
//    public static void CreateHitemSaving(JavaPlugin pl,HiItem ht,String id){
//        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
//        if(!file.getParentFile().exists()){
//            file.getParentFile().mkdirs();
//        }
//        if(!file.isFile()){
//            try {
//                file.createNewFile();
//                ObjectOutputStream ops = new ObjectOutputStream(new FileOutputStream(file,false));
//                ops.writeObject(ht);
//                ops.flush();
//                ops.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("��b[HiPlugin]��r[HitemObject]>>>��Ϊ"+pl.getName()+"���������Ʒ�洢ʱ���ִ���,�����Ѵ�ӡ!");
//                System.out.println("[HiPlugin]��־: ���ò����:"+pl.getName()+" ������Ʒ������: "+ht.getItemMeta().getDisplayName()+" Ŀ��·��: "+pl.getDataFolder()+"/HiPlugin_Hitems/"+"_"+id+".hitem");
//            }
//        }else{
//            throw new RuntimeException("[HiPlugin]>>>["+pl.getName()+"]"+"��Ʒid: "+id+" �Ѵ���!");
//        }
//
//
//    }
//
//    public static HiItem getHitemFromSaving(JavaPlugin pl,String id){
//        File file = new File(pl.getDataFolder()+"/HiPlugin_Hitems/"+id+".hitem");
//        if(file.isFile()){
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
//                try {
//                    Object obj = ois.readObject();
//                    if(obj instanceof HiItem){
//                        return (HiItem)obj;
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                    System.out.println("[HiPlugin]>>>"+pl.getName()+" : "+id);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("[HiPlugin]: "+pl.getName()+"����ڻ�ȡidΪ"+id+"����Ʒʱ��ȡʧ��! �Ҳ���Ŀ���ļ�");
//            }
//        }
//        return null;
//    }
}
