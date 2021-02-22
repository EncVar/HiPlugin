package top.whitecola;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import top.whitecola.commandhandler.HiCommand;
import top.whitecola.commands.SayHi;

import java.util.List;
import java.util.Vector;


public class HiPlugin extends JavaPlugin {
    {
        instance = this;
    }
    public static HiPlugin instance = null;
    public HiCommand commands;
    public Vector<Plugin> handlePlugins = new Vector<>();

    public void registerPlugin(Plugin pl){
        this.handlePlugins.add(pl);
    }

    @Override
    public void onLoad() {
        registerPlugin(this);
    }

    @Override
    public void onEnable() {
        System.out.println();
        System.out.println();
        System.out.println("��b"+"  ___ ___  .__  __________ .__                   .__         ");
        System.out.println("��b"+" /   |   \\ |__| \\______   \\|  |   __ __    ____  |__|  ____  ");
        System.out.println("��b"+"/    ~    \\|  |  |     ___/|  |  |  |  \\  / ___\\ |  | /    \\ ");
        System.out.println("��b"+"\\    Y    /|  |  |    |    |  |__|  |  / / /_/  >|  ||   |  \\");
        System.out.println("��b"+" \\___|_  / |__|  |____|    |____/|____/  \\___  / |__||___|  /");
        System.out.println("��b"+"       \\/                               /_____/           \\/ ");
        System.out.println();
        System.out.println("��b[HiPlugin]��fHiPlugin�ں˲�� ������.");
        System.out.println("��b[HiPlugin]��f����һ��ǰ�ò��,�������������߸����ɵĿ������,ͬʱҲ����߲��Ч�ʺ��ȶ���.");
        System.out.println("��b[HiPlugin]��fHiPlugin����������κ��������������,���������������ͬʱ����.");
        System.out.println("��b[HiPlugin]��fע��! HiPlugin�����Ϊ�ں˲��,�����������,���Ҷ�Ӧ�������!(��������HiPlugin����)");
        System.out.println("��b[HiPlugin]��fHiPlugin����: White_cola");
        System.out.println();
        System.out.println();
        initCommands();
    }



    public void initCommands(){
        commands = new HiCommand(HiPlugin.instance,"hip");
        commands.addCommand(new SayHi());
    }

    @Override
    public void onDisable() {
        if(commands!=null){
            commands.removeAllCommands();
        }
        System.out.println("��bHiPlugin�ѹر�.");
    }

//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        return commands.onCommand(sender,command,label,args);
//    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("hip")){
            return commands.onCommand(sender,command,label,args);
        }
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(command.getName().equalsIgnoreCase("hip")){
            return commands.onTabComplete(sender,command,alias,args);
        }
        return super.onTabComplete(sender, command, alias, args);
    }
}
