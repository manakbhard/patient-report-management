package com.skillenza.model;

public class UploadFileResponse {
	private String fileName;
    private String extractedFieldvalues;
    
//    public List<String> getExtractedFieldvalues() {
//		return extractedFieldvalues;
//	}
//
//	public void setExtractedFieldvalues(List<String> extractedFieldvalues) {
//		this.extractedFieldvalues = extractedFieldvalues;
//	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	//private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName,  String fileType, long size, String extractedFieldvalues) {
        this.fileName = fileName;
        this.setExtractedFieldvalues(extractedFieldvalues);
        //this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

	public String getExtractedFieldvalues() {
		return extractedFieldvalues;
	}

	public void setExtractedFieldvalues(String extractedFieldvalues) {
		this.extractedFieldvalues = extractedFieldvalues;
	}

}
