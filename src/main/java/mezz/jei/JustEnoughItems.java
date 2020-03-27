package mezz.jei;

import java.util.Map;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;

import mezz.jei.config.Constants;
import mezz.jei.config.ServerInfo;
import mezz.jei.startup.ProxyCommon;

@Mod(modid = Constants.MOD_ID,
	name = Constants.NAME,
	version = Constants.VERSION,
	guiFactory = "mezz.jei.config.JEIModGuiFactory",
	acceptedMinecraftVersions = "[1.12.2,1.13)",
	dependencies = "required-after:forge@[14.23.5.2816,);")
public class JustEnoughItems {

	@SuppressWarnings("NullableProblems")
	@SidedProxy(clientSide = "mezz.jei.startup.ProxyCommonClient", serverSide = "mezz.jei.startup.ProxyCommon")
	private static ProxyCommon proxy;

	public static ProxyCommon getProxy() {
		return proxy;
	}

	@NetworkCheckHandler
	public boolean checkModLists(Map<String, String> modList, Side side) {
		if (side == Side.SERVER) {
			boolean jeiOnServer = modList.containsKey(Constants.MOD_ID);
			ServerInfo.onConnectedToServer(jeiOnServer);
		}

		return true;
	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@Mod.EventHandler
	public void loadComplete(FMLLoadCompleteEvent event) {
		proxy.loadComplete(event);
	}
}
