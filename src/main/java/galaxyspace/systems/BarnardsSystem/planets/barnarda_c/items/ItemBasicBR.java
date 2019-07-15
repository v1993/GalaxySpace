package galaxyspace.systems.BarnardsSystem.planets.barnarda_c.items;

import java.util.List;

import javax.annotation.Nullable;

import galaxyspace.GalaxySpace;
import galaxyspace.core.util.GSCreativeTabs;
import galaxyspace.systems.BarnardsSystem.core.registers.BRBlocks;
import galaxyspace.systems.BarnardsSystem.planets.barnarda_c.blocks.Barnarda_C_Logs;
import galaxyspace.systems.BarnardsSystem.planets.barnarda_c.world.gen.WorldGenTree_Swampland;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBasicBR extends Item implements ISortableItem{

	public static String[] names = 
	{ 
		"violet_reeds",
		"yellow_fruits",
		"debugger"
	};
	
	public ItemBasicBR()
	{
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("br_basic");
		this.setCreativeTab(GSCreativeTabs.GSItemsTab);
	}
	
	@Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> list)    
    {
    	if (tab == GSCreativeTabs.GSItemsTab || tab == CreativeTabs.SEARCH)
        {
	        for (int i = 0; i < this.names.length; i++)
	        {
	        	if(!this.names[i].equals("null"))
	        		list.add(new ItemStack(this, 1, i));
	        }
        }
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		RayTraceResult raytraceresult = this.rayTrace(world, player, true);
		if(raytraceresult == null)
			return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
		
		BlockPos pos = raytraceresult.getBlockPos();
		
		GalaxySpace.debug(pos + "");
		if(world.isAirBlock(pos.up())) {
			new WorldGenTree_Swampland(BRBlocks.BARNARDA_C_TEST_LOG.getDefaultState().withProperty(Barnarda_C_Logs.LOG_AXIS, Barnarda_C_Logs.EnumAxis.NONE), BRBlocks.BARNARDA_C_LEAVES.getDefaultState(), world.rand.nextInt(3)).generate(world, world.rand, pos.up());
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flagIn) {
	
	}
	
	@Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
    	return "item." + this.names[par1ItemStack.getItemDamage()];
    }
	
	@Override
	public int getMetadata(int par1) {
		return par1;
	}
	
	@Override
	public EnumSortCategoryItem getCategory(int meta) {
		return EnumSortCategoryItem.GENERAL;
	}	
	
}