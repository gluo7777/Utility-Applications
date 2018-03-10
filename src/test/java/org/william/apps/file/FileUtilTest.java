package org.william.apps.file;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.william.apps.experiment.testutils.FileTestUtil.*;
import static org.william.apps.file.FileUtil.*;
public class FileUtilTest extends FileTest{

	@Test
	public void test_pathToCopy() throws IOException {
		String name = "my_bunny_but";
		Path old = createFile(name);
		Path file = pathToCopy(old, "_copy");
		assertEquals(old.getFileName().toString().concat("_copy"), file.getFileName().toString());
		assertEquals(old.getParent(), file.getParent());
	}

	@Test
	public void test_simpleFileName(){
		String name = "my_bunny_but";
		Path old = getTestDirPath().resolve(name);
		assertEquals(name, simpleFileName(old));
	}
	
	@Test
	public void test_folderNotEmpty() throws IOException{
		assertFalse(folderNotEmpty(getTestDirPath()));
		String name = "my_bunny_but";
		Path p = createFile(name);
		assertTrue(folderNotEmpty(getTestDirPath()));
		deleteFile(p);
		assertFalse(folderNotEmpty(getTestDirPath()));
	}
	
	@Test
	public void test_writeStringToFile() throws IOException{
		String name = "my_bunny_but";
		Path p = createFile(name);
		FileUtils.writeStringToFile(p.toFile(), "aabbcc");
		assertEquals("aabbcc", FileUtils.readFileToString(p.toFile()));
		FileUtils.writeStringToFile(p.toFile(), "zzzzzz");
		assertEquals(getTestDirPath().toFile().list().length, 1);
		assertEquals("zzzzzz", FileUtils.readFileToString(p.toFile()));
	}
	
	@Test
	public void test_findReplaceInFile() throws IOException{
		String name = "my_bunny_but";
		String content = "afdaf3424fvdsfsf12313  _\n\n\t\t3445dfsf";
		Path p = createFile(name);
		FileUtils.writeStringToFile(p.toFile(), content);
		assertEquals(content, FileUtils.readFileToString(p.toFile()));
		assertEquals(3, findReplaceInFile(p, "(\\d+)", "$1~"));
		content = "afdaf3424~fvdsfsf12313~  _\n\n\t\t3445~dfsf";
		assertEquals(content, FileUtils.readFileToString(p.toFile()));
		assertEquals(getTestDirPath().toFile().list().length, 1);
	}
}
