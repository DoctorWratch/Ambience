package vazkii.ambience.Util;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.ambience.Ambience;
import vazkii.ambience.blocks.AlarmTileEntity;
import vazkii.ambience.blocks.SpeakerTileEntity;

public class ModTileEntityTypes {
	
	//public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Ambience.MODID);
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Ambience.MODID);
	 
	public static final RegistryObject<TileEntityType<SpeakerTileEntity>> SPEAKER = TILE_ENTITY_TYPES.register("speaker", () -> TileEntityType.Builder.create(SpeakerTileEntity::new,RegistryHandler.Speaker.get()).build(null));
	
	//public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM = TILE_ENTITY_TYPES.register("alarm", () -> TileEntityType.Builder.create(AlarmTileEntity::new,RegistryHandler.Alarm.get()).build(null));
	
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_WHITE = TILE_ENTITY_TYPES.register("alarm_white", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("white"),RegistryHandler.block_Alarm_WHITE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_RED = TILE_ENTITY_TYPES.register("alarm_red", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("red"),RegistryHandler.block_Alarm_RED.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_ORANGE = TILE_ENTITY_TYPES.register("alarm_orange", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("orange"),RegistryHandler.block_Alarm_ORANGE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_YELLOW = TILE_ENTITY_TYPES.register("alarm_yellow", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("yellow"),RegistryHandler.block_Alarm_YELLOW.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_LIME = TILE_ENTITY_TYPES.register("alarm_lime", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("lime"),RegistryHandler.block_Alarm_LIME.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_GREEN = TILE_ENTITY_TYPES.register("alarm_green", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("green"),RegistryHandler.block_Alarm_GREEN.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_LIGHTBLUE = TILE_ENTITY_TYPES.register("alarm_lightblue", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("lightblue"),RegistryHandler.block_Alarm_LIGHTBLUE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_CYAN = TILE_ENTITY_TYPES.register("alarm_cyan", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("cyan"),RegistryHandler.block_Alarm_CYAN.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_BLUE = TILE_ENTITY_TYPES.register("alarm_blue", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("blue"),RegistryHandler.block_Alarm_BLUE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_PURPLE = TILE_ENTITY_TYPES.register("alarm_purple", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("purple"),RegistryHandler.block_Alarm_PURPLE.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_MAGENTA = TILE_ENTITY_TYPES.register("alarm_magenta", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("magenta"),RegistryHandler.block_Alarm_MAGENTA.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_PINK = TILE_ENTITY_TYPES.register("alarm_pink", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("pink"),RegistryHandler.block_Alarm_PINK.get()).build(null));
	public static final RegistryObject<TileEntityType<AlarmTileEntity>> ALARM_BROWN = TILE_ENTITY_TYPES.register("alarm_brown", () -> TileEntityType.Builder.create(() -> new AlarmTileEntity("brown"),RegistryHandler.block_Alarm_BROWN.get()).build(null));
	
	
	public static TileEntityType<AlarmTileEntity> getAlarmByColor(String color) {
		
		switch(color) {
			case "white" : return ModTileEntityTypes.ALARM_WHITE.get();
			case "red" :return ModTileEntityTypes.ALARM_RED.get();
			case "yellow" :return ModTileEntityTypes.ALARM_YELLOW.get();
			case "orange" :return ModTileEntityTypes.ALARM_ORANGE.get();
			case "lime" : return ModTileEntityTypes.ALARM_LIME.get();
			case "green" :return ModTileEntityTypes.ALARM_GREEN.get();
			case "cyan" :return ModTileEntityTypes.ALARM_CYAN.get();		
			case "lightblue" :return ModTileEntityTypes.ALARM_LIGHTBLUE.get();
			case "blue" :return ModTileEntityTypes.ALARM_BLUE.get();
			case "purple" :return ModTileEntityTypes.ALARM_PURPLE.get();
			case "magenta" :return ModTileEntityTypes.ALARM_MAGENTA.get();
			case "pink" :return ModTileEntityTypes.ALARM_PINK.get();
			case "brown" :return ModTileEntityTypes.ALARM_BROWN.get();
		}
		
		return null;		
	}


	public static TileEntityType<?> getAlarmByColor2(String color) {
		return ModTileEntityTypes.ALARM_RED.get();
	}
	
}