package org.eobjects.datacleaner.repository.s3;

import java.io.OutputStream;
import java.util.List;

import org.eobjects.datacleaner.repository.AbstractRepositoryNode;
import org.eobjects.datacleaner.repository.RepositoryFile;
import org.eobjects.datacleaner.repository.RepositoryFolder;
import org.eobjects.metamodel.util.Action;

public class AmazonS3RepositoryFolder extends AbstractRepositoryNode implements RepositoryFolder {

	private static final long serialVersionUID = 1L;

	public RepositoryFolder getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete() throws IllegalStateException {
		// TODO Auto-generated method stub
		
	}

	public List<RepositoryFolder> getFolders() {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFolder getFolder(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFolder getOrCreateFolder(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RepositoryFile> getFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RepositoryFile> getFiles(String prefix, String extension) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFile getLatestFile(String prefix, String extension) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFile getFile(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFile createFile(String name,
			Action<OutputStream> writeCallback) {
		// TODO Auto-generated method stub
		return null;
	}

	public RepositoryFolder createFolder(String name)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
