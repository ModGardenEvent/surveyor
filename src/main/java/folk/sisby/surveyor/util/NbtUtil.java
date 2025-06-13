package folk.sisby.surveyor.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.Collection;

public class NbtUtil {
	public static void removeRecursive(NbtCompound nbt, Collection<String> keys) {
		keys.forEach(nbt::remove);
		for (String key : nbt.getKeys()) {
			if (nbt.getCompound(key).isPresent()) {
				removeRecursive(nbt.getCompound(key).orElseThrow(), keys);
			} else if (nbt.getList(key).isPresent()) {
				for (NbtElement listNbt : nbt.getListOrEmpty(key)) {
					removeRecursive((NbtCompound) listNbt, keys);
				}
			}
		}
	}
}
