package de.prokyo.network.swaggen.plugin.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.ClassFile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a class loader which loads .class files and converts them into javassist CtClasses.
 */
@RequiredArgsConstructor
public class ClassLoader {

	private final File classesRoot;

	@Getter private final List<CtClass> classes = new ArrayList<>();

	/**
	 * Loads the classes of the <i>classesRoot</i> directory and adds them to <i>classes</i>.
	 * @see ClassLoader#findClasses(List, File)
	 */
	@SneakyThrows
	public void loadClasses() {
		this.findClasses(this.classes, this.classesRoot);
	}

	/**
	 * Loads the classes of the given directory and adds them to the given list.
	 *
	 * @param classes The target list for the found classes.
	 * @param dir The directory where this method should search in (recursively).
	 */
	private void findClasses(List<CtClass> classes, File dir) throws MalformedURLException, IOException {
		File[] files = dir.listFiles();
		if(files == null) return;

		for (File file : files) {
			if (file.isDirectory()) {
				this.findClasses(classes, file);
				continue;
			}

			if(!file.getName().endsWith(".class")) continue;

			classes.add(ClassPool.getDefault().makeClass(new ClassFile(new DataInputStream(file.toURI().toURL().openStream()))));
			System.out.println(file);
		}
	}
}
