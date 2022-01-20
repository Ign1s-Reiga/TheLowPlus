package dev.reiga.thelowplus;

import dev.reiga.thelowplus.command.CommandNCount;
import dev.reiga.thelowplus.command.CommandNReset;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(modid = "thelowplus", name = "TheLowPlus", version = "1.0")
public class TheLowPlus {
    @Mod.Instance("thelowplus")
    public static TheLowPlus instance;

    private final Logger logger = LogManager.getLogger();
    private final int maxTitleView = 11;
    public int titleViewCount = 0;
    public List<Integer> count = new ArrayList<>();
    public boolean isCount = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        isCount = config.getBoolean("isCount", "general", true, "");
        config.save();
        logger.info("PreInit");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new CommandNCount());
        ClientCommandHandler.instance.registerCommand(new CommandNReset());
        logger.info("Init");
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderAllOverlay(RenderGameOverlayEvent event) {
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        int height = fontRenderer.FONT_HEIGHT;
        fontRenderer.drawString("titleCount: " + count.stream().mapToInt(v -> v).sum(), 2, height + 1, 14737632);
        fontRenderer.drawString("isCount: " + isCount, 2, height + 8, 14737632);
        fontRenderer.drawString("isCount: " + getNotReleasedNum(), 2, height + 15, 14737632);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderTitle(RenderGameOverlayEvent event) throws IllegalAccessException {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            String title = (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
            Arrays.stream(NumeralsList.values())
                    .forEach(v -> {
                        if (title.contains(v.getRomeNumerals()) && !count.contains(v.getArabicNumerals())) {
                            count.add(v.getArabicNumerals());
                            titleViewCount++;
                        }
                    });
        }
    }

    private int getNotReleasedNum() {
        final int sumCount = 78;
        return sumCount - count.stream().mapToInt(v -> v).sum();
    }
}
