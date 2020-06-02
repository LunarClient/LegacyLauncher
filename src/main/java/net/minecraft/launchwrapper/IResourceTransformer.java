package net.minecraft.launchwrapper;

public interface IResourceTransformer {

    byte[] transform(String resourcePath, byte[] original);

}
