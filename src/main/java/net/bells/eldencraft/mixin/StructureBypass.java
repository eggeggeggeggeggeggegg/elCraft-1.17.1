package net.bells.eldencraft.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StructureBlockEntity.class)
public class StructureBypass {

    @Inject(at = @At(value = "HEAD"), method = "load")
    protected void load(CompoundTag pPTag, CallbackInfo info) {
        int j = Mth.clamp(pPTag.getInt("posY"), -480, 1000);
    }
}
