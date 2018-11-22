CString CScanDlg::EncodeImage()
{//对图片进行Base64编码
    ZBase64 zBase;
    //图片编码
    CxImage  image;   // 定义一个CxImage对象    
    image.Load(this->m_strImgPath, CXIMAGE_FORMAT_JPG);   //先装载jpg文件,需要指定文件类型
    long size=0;//得到图像大小
    BYTE* buffer=0;//存储图像数据的缓冲
    image.Encode(buffer,size,CXIMAGE_FORMAT_JPG);//把image对象中的图像以type类型数据copy到buffer
    string strTmpResult=zBase.Encode(buffer,size);
    CString result;
    result = strTmpResult.c_str();
    return result;
}

void CScanDlg::DecodeImageData(CString strData)
{//对Base64编码过的数据解码并显示原图片

    ZBase64 zBase;
    int OutByte=0;
    string strTmpResult=zBase.Decode(strData,strData.GetLength(),OutByte);
    int i,len = strTmpResult.length();
    BYTE *buffer = new BYTE[len];
    for (i=0;i<len;++i)
    {
        buffer[i] = strTmpResult[i];
    }
    CxImage image(buffer,len,CXIMAGE_FORMAT_JPG);//把内存缓冲buffer中的数据构造成Image对象
    delete [] buffer;
    CDC* hdc = m_picture.GetDC();
    m_bitmap = image.MakeBitmap(hdc->m_hDC);
    HBITMAP h0ldBmp = m_picture.SetBitmap(m_bitmap);
    if(h0ldBmp) DeleteObject(h0ldBmp);
    if(hdc->m_hDC) m_picture.ReleaseDC(hdc);
    if(m_bitmap) DeleteObject(m_bitmap);
}
