package vazkii.ambience.blocks;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.ambience.Ambience;
import vazkii.ambience.Util.ModTileEntityTypes;
import vazkii.ambience.network.AmbiencePackageHandler;
import vazkii.ambience.network.MyMessage;

public class SpeakerTileEntity extends TileEntity implements ITickableTileEntity{

	public int cooldown;
	public String selectedSound = "";
	public boolean isPowered = false;
	public int delay = 30;
	public boolean loop = true;
	public int distance = 1;
	public int countPlay = 0;
	public boolean sync = false;
	public int songLenght = 0;
	private String old_song = "";
	private boolean isAlarm=false;
	public String color = "";
	public boolean isOn=false;
	
	public SpeakerTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);		
		
	}
	
	public SpeakerTileEntity(String Color) {
		this(ModTileEntityTypes.SPEAKER.get());
		
		this.isAlarm=true;
		
		this.color=Color;
		cooldown = 0;
		delay = 30;
	}
	
	public SpeakerTileEntity() {
		this(ModTileEntityTypes.SPEAKER.get());
		
		cooldown = 0;
		delay = 30;
	} 
	
	public SpeakerTileEntity(boolean isAlarmL, String colorL,boolean on) {
		this(ModTileEntityTypes.SPEAKER.get());
		
		cooldown = 0;
		delay = 30;
		color=colorL;
		isAlarm=isAlarmL;
		isOn=on; //Isso faz a luz saber que esta ligada assim que voce coloca um alarm_lit no chao entao ele apaga (talvez tirar dps se eu quiser manter luzes acessas)
	}
	
	@Override
	public void read(CompoundNBT nbt) {
		this.cooldown = nbt.getInt("cooldown");
		this.delay = nbt.getInt("delay");
		this.selectedSound = nbt.getString("sound");
		this.loop = nbt.getBoolean("loop");
		this.distance = nbt.getInt("distance");
		this.color = nbt.getString("color");
		this.isAlarm = nbt.getBoolean("isAlarm");
		super.read(nbt);

		old_song = selectedSound;
	}

	@Override
	public CompoundNBT write(CompoundNBT nbt) {
		nbt.putInt("cooldown", this.cooldown);
		nbt.putInt("delay", this.delay);
		nbt.putString("sound", selectedSound);
		nbt.putBoolean("loop", this.loop);
		nbt.putFloat("distance", this.distance);
		nbt.putString("color", this.color);
		nbt.putBoolean("isAlarm", this.isAlarm);

		return super.write(nbt);
	}
	
	/*int countLight=0;
	Speaker parent;
	private void UpdateLight(boolean syncWithSound) {
		
		if(parent == null) {
			Block blockAlarm=this.world.getBlockState(pos).getBlock();
			if(blockAlarm.getRegistryName().getPath().contains("alarm"))
				parent=(Speaker) blockAlarm;
		}else {
		
			if(isAlarm)	
				this.countLight++;
				
				if(countLight>17 & isOn & isAlarm) {											
					parent.setState(false, this.world, this.pos, this.color);	
					isOn=false;
				}	
				
			if(isAlarm & songLenght>2 & !syncWithSound)
			{	
				if((countLight>0 & countLight<17) & world.isBlockPowered(pos) & !isOn) {
					parent.setState(true, this.world, this.pos, this.color);		
					isOn=true;
				}
				
				if(countLight>30)
					countLight=0;
			}
					
			if(syncWithSound & songLenght<=2) {		
				parent.setState(true, this.world, this.pos, this.color);		
				isOn=true;	
				this.countLight=0;		
			}	
			
			//Desliga a luz caso n�o receba sinal de redstone
			if(world.isBlockPowered(pos) & isOn) {
				isOn=false;
				parent.setState(false, this.world, this.pos, this.color);
			}	
		}
	}
*/
	@Override
	public void tick() {
		

		
		//UpdateLight(false);
		
		try {
			if (songLenght == 0 & selectedSound != "")
				getSongLenght();
	
					
			if (!this.getWorld().isRemote & cooldown>0) 
			{
				this.cooldown--;			
			}
						
			if (!this.getWorld().isRemote & cooldown == 0) {
			
				if (loop) // Play infinitly
					if (world.isBlockPowered(pos)) {
						this.cooldown =  delay + (songLenght * 20);

						Speaker.selectedSound=selectedSound;
						//UpdateLight(true);
								
						this.getWorld().playSound((PlayerEntity) null, this.pos.getX(), this.pos.getY(), this.pos.getZ(),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambience:" + selectedSound)),
								SoundCategory.NEUTRAL, (float) distance, (float) 1);
					}
	
				if (!loop & countPlay == 0)// Play one time if loop is disabled
				{
					if (world.isBlockPowered(pos)) {
	
						Speaker.selectedSound=selectedSound;
						
						this.getWorld().playSound((PlayerEntity) null, this.pos.getX(), this.pos.getY(), this.pos.getZ(),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambience:" + selectedSound)),
								SoundCategory.NEUTRAL, (float) distance, (float) 1);
						countPlay++;
					}
				}
	
			}
	
			if (sync & !this.getWorld().isRemote) {
				sync = false;
				// Updates client
				
				CompoundNBT nbt = new CompoundNBT();
				nbt.putString("selectedSound", selectedSound);
				nbt.putInt("delay", delay);
				nbt.putBoolean("loop", loop);
				nbt.putBoolean("sync", true);
				nbt.putString("color", this.color);
				nbt.putInt("distance", distance);

				AmbiencePackageHandler.sendToAll(new MyMessage(nbt));
				Speaker.selectedSound=selectedSound;
				markDirty();
	
				if (!old_song.contains(selectedSound)) {
					old_song = selectedSound;
					cooldown = 0;
				}
	
				// Obt�m o tempo do som selecionado********************
				getSongLenght();
				// ****************************************************
				if (cooldown == 0) {
					if (world.isBlockPowered(pos)) {
						this.cooldown = delay + (songLenght* 20);
						this.getWorld().playSound((PlayerEntity) null, this.pos.getX(), this.pos.getY(), this.pos.getZ(),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("ambience:" + selectedSound)),
								SoundCategory.NEUTRAL, (float) distance, (float) 1);
					}
				}
	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getSongLenght() {
		/*
		 // Obt�m o tempo do som selecionado********************
		String selectedsound = ((SpeakerTileEntity) world.getTileEntity(pos)).selectedSound;
		File f = new File(Ambience.resourcesDir+"\\sounds", selectedsound + ".ogg");

		if (f.isFile()) {
			try {
				AudioFile af = AudioFileIO.read(f);
				AudioHeader ah = af.getAudioHeader();
				songLenght = ah.getTrackLength();
			} catch (Exception e) {

			}
		}else {
			songLenght=0;
		}*/
		// ****************************************************
	}

}
