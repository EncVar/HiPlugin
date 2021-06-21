package top.whitecola.commands;

import com.sun.istack.internal.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.whitecola.annotations.ItsACommand;
import top.whitecola.commandhandler.ICommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ItsACommand(CommandNmae = "sayHi",premission = "hiplugin.sayhi")
public class SayHi implements ICommand {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length!=2){
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(target!=null&&target.isOnline()){
            if(commandSender.getName().equalsIgnoreCase(args[1])){
                commandSender.sendMessage("�������Լ��ʺ�!");
                return true;
            }
            commandSender.sendMessage("����"+target.getName()+"�ʺ�!");
            if(commandSender instanceof Player){
                target.sendMessage("���"+commandSender.getName()+"�����ʺ�!");
                return true;
            }else{
                target.sendMessage("������"+"�����ʺ�!");
                return true;
            }
        }else{
            commandSender.sendMessage("���"+args[0]+"�����ڻ�����!");
            return true;
        }

    }

    @Override
    public @NotNull
    List<String> getArgs() {
        return Arrays.asList("[Player]");
    }



    @Override
    public @NotNull List<String> handleArg(CommandSender sender,String handleArg) {
        if(handleArg.equalsIgnoreCase("[Player]")){
            List<String> pn = new ArrayList<>();
            for(Player pl : Bukkit.getOnlinePlayers()){
                pn.add(pl.getName());
            }
            return pn;
        }

        return Arrays.asList("");
    }


    @Override
    public @NotNull String getUsage() {
        return "/hip sayhi <Player>";
    }

    @Override
    public @NotNull String getUsageDescripition() {
        return "��ָ������ʺ�";
    }



}
