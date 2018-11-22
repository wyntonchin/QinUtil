CString CScanDlg::EncodeImage()
{//��ͼƬ����Base64����
    ZBase64 zBase;
    //ͼƬ����
    CxImage  image;   // ����һ��CxImage����    
    image.Load(this->m_strImgPath, CXIMAGE_FORMAT_JPG);   //��װ��jpg�ļ�,��Ҫָ���ļ�����
    long size=0;//�õ�ͼ���С
    BYTE* buffer=0;//�洢ͼ�����ݵĻ���
    image.Encode(buffer,size,CXIMAGE_FORMAT_JPG);//��image�����е�ͼ����type��������copy��buffer
    string strTmpResult=zBase.Encode(buffer,size);
    CString result;
    result = strTmpResult.c_str();
    return result;
}

void CScanDlg::DecodeImageData(CString strData)
{//��Base64����������ݽ��벢��ʾԭͼƬ

    ZBase64 zBase;
    int OutByte=0;
    string strTmpResult=zBase.Decode(strData,strData.GetLength(),OutByte);
    int i,len = strTmpResult.length();
    BYTE *buffer = new BYTE[len];
    for (i=0;i<len;++i)
    {
        buffer[i] = strTmpResult[i];
    }
    CxImage image(buffer,len,CXIMAGE_FORMAT_JPG);//���ڴ滺��buffer�е����ݹ����Image����
    delete [] buffer;
    CDC* hdc = m_picture.GetDC();
    m_bitmap = image.MakeBitmap(hdc->m_hDC);
    HBITMAP h0ldBmp = m_picture.SetBitmap(m_bitmap);
    if(h0ldBmp) DeleteObject(h0ldBmp);
    if(hdc->m_hDC) m_picture.ReleaseDC(hdc);
    if(m_bitmap) DeleteObject(m_bitmap);
}
