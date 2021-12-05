package top.whitecola.commandhandler;


import org.apache.commons.lang.IllegalClassException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.utils.CommandUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class HiCommand implements TabExecutor {
    public Vector<ICommand> commands = new Vector<>();
    public String startWithCommand;
    public Plugin pl;
    private PluginCommand pluginCommand;
    public HiCommand(Plugin pl, String startWithCommand){
        this.pl = pl;
        this.startWithCommand = startWithCommand;
        registerThis(startWithCommand,pl);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!this.pl.isEnabled()){
            sender.sendMessage("��f��l[HiPlugin]>>["+this.pl.getName()+"]"+"�����û������,�޷�ʹ�ô˲������������!");
            return true;
        }
        if(args.length>0){
            ICommand ic = this.getCommandByName(args[0]);
            if(ic==null){
                sender.sendMessage("��f��l[HiPlugin]>>["+this.pl.getName()+"]��bû����"+startWithCommand+"������,����������"+args[0]+"!");
                return true;
            }
            if(!getCommandPremission(ic).trim().equals("") && !sender.hasPermission(getCommandPremission(ic).trim())){
                sender.sendMessage("��f��l[HiPlugin]>>["+this.pl.getName()+"]��b��û��Ȩ��������,����Ҫ"+getCommandPremission(ic)+"Ȩ��!");
                return true;
            }
            boolean result = false;
            try {

                result = ic.onCommand(sender, command, label, args);
            }catch (Throwable e){
                e.printStackTrace();
                System.out.println("��f��l[HiPlugin]��c�ڴ���"+this.pl.getName()+"�����"+this.startWithCommand+"�����������"+getCommandName(ic)+"ʱ���ִ���,������־�Ѵ�ӡ!");
                System.out.println("��f��l[HiPlugin]��c�������Ϊ���Ǹ�����,����ϵ"+this.pl.getName()+"�Ŀ�����.");
            }
            if(!result){
                if(ic.getUsage().trim().equals("") || ic.getUsageDescripition().trim().equals("")){
                    return false;
                }
                sender.sendMessage("��f��l[HiPlugin]>>["+this.pl.getName()+"]��b"+"�÷�: "+ic.getUsage()+" - "+ic.getUsageDescripition());
                return true;
            }
                return true;


        }
        return false;
    }



    public ICommand getCommandByName(String com){
        for(ICommand ic : commands){
            if(getCommandName(ic).equalsIgnoreCase(com)){
                return ic;
            }
        }
        return null;
    }

    public void addCommand(ICommand icom){
        try {
            if (!isALegalCommand(icom)) {
                throw new IllegalClassException("��a[HiPlugin]" + getCommandName(icom) + "��,û��@ItsCommandע��,�޷�ע��! ��Դ: " + pl.getName() + "���.");
            }
            commands.add(icom);
            System.out.println("��a[HiPlugin]" + "�ɹ�Ϊ" + this.pl.getName() + "���ע��" + this.startWithCommand + "�µ�������: " + getCommandName(icom) + " ��b" + "[��Э���˲�����õĴ����������tab��ʾ����!]");
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    public void addCommand(ICommand icom,String permission,String permissionDefault){

        pluginCommand.setPermission(permission);
        pluginCommand.setPermissionMessage(permissionDefault);

        try {
            if (!isALegalCommand(icom)) {
                throw new IllegalClassException("��a[HiPlugin]" + getCommandName(icom) + "��,û��@ItsCommandע��,�޷�ע��! ��Դ: " + pl.getName() + "���.");
            }
            commands.add(icom);
            System.out.println("��a[HiPlugin]" + "�ɹ�Ϊ" + this.pl.getName() + "���ע��" + this.startWithCommand + "�µ�������: " + getCommandName(icom) + " ��b" + "[��Э���˲�����õĴ����������tab��ʾ����!]");
        }catch (Throwable e){
            e.printStackTrace();
        }
    }



    public void removeCommandByName(String com){
        this.commands.remove(getCommandByName(com));
    }

    public void removeAllCommands(){
        this.commands.clear();
        System.out.println("��a[HiPlugin]��Ϊ"+this.pl.getName()+"���ж��"+this.pl.getName()+"�����"+this.startWithCommand+"�����µ�����������!");
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        if(!this.pl.isEnabled()){
            commandSender.sendMessage("��f��l[HiPlugin]>>["+this.pl.getName()+"]"+"��c�����û������,�޷�ʹ�ô˲������������!");
            return Arrays.asList("");
        }
        if(args.length>1){
            ICommand ic = getCommandByName(args[0]);
            if(ic==null){
                return Arrays.asList("");
            }


            if(commandSender instanceof Player &&!commandSender.hasPermission(getCommandPremission(ic))){
                return Arrays.asList("");
            }

            if(args.length-1>ic.getArgs().size()){
                return Arrays.asList("");
            }
            try {
                String temp = ic.getArgs().get(args.length - 2).trim();
                if(temp.contains("/")){
                    return Arrays.asList(temp.split("/"));
                }
                return ic.handleArg(commandSender,temp);
            }catch (Throwable e){
                e.printStackTrace();
                System.out.println("��c[HiPlugin]�ڴ���"+this.pl.getName()+"�����tab����ʱ���ִ���!");
                System.out.println("��c[HiPlugin]Ϊ��֤��ȫ����,HiPlugin���Զ�������tab����ȡ��!");
                System.out.println("��c[HiPlugin]�������Ҫ����������,����ϵ "+this.pl.getName()+"����Ŀ�����!");
                System.out.println("��c[HiPlugin]������־: "+"��a������: "+this.pl.getName()+"  ��������"+startWithCommand+"��������"+getCommandName(ic)+" ���������: "+(args.length - 2)+" ʵ��arg����: "+args.length);
                return Arrays.asList("");
            }
        }
        return getAllCommandsName();

    }




    public List<String> getAllCommandsName(){
        List<String> list = new ArrayList<>();
        for(ICommand ic : this.commands){
            list.add(getCommandName(ic));
        }
        if(list==null || list.isEmpty()){
            return Arrays.asList("");
        }
        return list;
    }

    public static String getCommandName(ICommand icom){
        if(isALegalCommand(icom)){
            return icom.getClass().getAnnotation(ItsACommand.class).CommandNmae();
        }
        return "";
    }

    public static boolean isALegalCommand(ICommand icom){
        if(icom == null)
            return false;
        Class<?> clazz = icom.getClass();
        if(clazz.isAnnotationPresent(ItsACommand.class)){
            if(!clazz.getAnnotation(ItsACommand.class).CommandNmae().equals("")){
                return true;
            }
        }
        return false;

    }

    public static String getCommandPremission(ICommand icom){
        if(!isALegalCommand(icom)){
            return "hiplugin.example";
        }
        return icom.getClass().getAnnotation(ItsACommand.class).premission();
    }


    public void registerThis(String prefix,Plugin pl){
//        CommandUtils.registerCommandToBukkit(startWithCommand,);
        pluginCommand = CommandUtils.newAPluginCommand(startWithCommand,pl);
        pluginCommand.setExecutor(pl);
        pluginCommand.setTabCompleter(pl);
        pluginCommand.setUsage("/"+prefix +" <commands> [args] ...");
        CommandUtils.registerCommandToBukkit(prefix,pluginCommand);
    }

}
