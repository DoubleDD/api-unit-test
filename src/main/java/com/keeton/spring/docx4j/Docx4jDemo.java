package com.keeton.spring.docx4j;

import com.keeton.spring.MultiThreadDownload;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import javax.xml.bind.JAXBElement;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成word文档测试
 */
public class Docx4jDemo {

    private final static String user_home = System.getProperty("user.home");

    public static void main(String[] args) throws Exception {
        WordprocessingMLPackage word             = WordprocessingMLPackage.createPackage();
        ObjectFactory           factory          = Context.getWmlObjectFactory();
        MainDocumentPart        mainDocumentPart = word.getMainDocumentPart();

        P    p0    = factory.createP();
        R    r0    = factory.createR();
        Text text0 = factory.createText();
        text0.setValue("标题");
        setRStyle(r0);
        setParaJcAlign(p0, JcEnumeration.CENTER);
        r0.getContent().add(text0);
        p0.getContent().add(r0);
        mainDocumentPart.addObject(p0);


        // 创建表格元素
        Tbl table = factory.createTbl();
        addBorders(table);

        for (int i = 0; i < 3; i++) {
            Tr tr = factory.createTr();
            // 第一列
            Tc tc = factory.createTc();
//            P  p1  = mainDocumentPart.createParagraphOfText("---row" + i + "---column 1---");
            P    p1    = factory.createP();
            R    r1    = factory.createR();
            Text text1 = factory.createText();
            text1.setValue("---row" + i + "---column 1---");

            r1.getContent().add(text1);
            p1.getContent().add(r1);
            tc.getContent().add(p1);
            if (i == 2) {
                // 添加图片
                P withImage = addInlineImageToParagraph(createInlineImage(word, "http://172.30.1.211:30101/oss/default/riverChief/2022-08-1717:57:43turang_panel.86ad0baf.png"));
                tc.getContent().add(withImage);
            }
            tr.getContent().add(tc);
            setCellWidth(tc, 3000);

            // 第2列
            Tc tc2 = factory.createTc();
            P  p2  = mainDocumentPart.createParagraphOfText("---row" + i + "---column 2---");
//            P p2 = factory.createP();
            tc2.getContent().add(p2);
            tr.getContent().add(tc2);
            setCellWidth(tc2, 6000);


            table.getContent().add(tr);
        }
        // 合并单元格
        mergeCellsHorizontal(table, 2, 0, 1);

        mainDocumentPart.addObject(table);


        File file = new File(user_home + "/docx4j.docx");
        System.out.println(file.getAbsoluteFile());
        word.save(file);
    }

    /**
     * 创建一个对象工厂并用它创建一个段落和一个可运行块R.
     * 然后将可运行块添加到段落中. 接下来创建一个图画并将其添加到可运行块R中. 最后我们将内联
     * 对象添加到图画中并返回段落对象.
     *
     * @param inline 包含图片的内联对象.
     * @return 包含图片的段落
     */
    private static P addInlineImageToParagraph(Inline inline) {
        // 添加内联对象到一个段落中
        ObjectFactory factory   = new ObjectFactory();
        P             paragraph = factory.createP();
        R             run       = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

    /**
     * 使用给定的文件创建一个内联图片.
     * 跟前面例子中一样, 我们将文件转换成字节数组, 并用它创建一个内联图片.
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static Inline createInlineImage(WordprocessingMLPackage wordprocessingMLPackage, File file) throws Exception {
        byte[] bytes = convertImageToByteArray(file);

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordprocessingMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;

        return imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);
    }

    private static Inline createInlineImage(WordprocessingMLPackage wordprocessingMLPackage, String imageUrl) throws Exception {
        byte[] bytes = convertImageToByteArray(imageUrl);

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordprocessingMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;

        return imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);
    }

    private static byte[] convertImageToByteArray(String imageUrl) {
        return MultiThreadDownload.downloadBytes(imageUrl);
    }


    /**
     * 将图片从文件对象转换成字节数组.
     *
     * @param file 将要转换的文件
     * @return 包含图片字节数据的字节数组
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static byte[] convertImageToByteArray(File file)
            throws FileNotFoundException, IOException {
        InputStream is     = new FileInputStream(file);
        long        length = file.length();
        // 不能使用long类型创建数组, 需要用int类型.
        if (length > Integer.MAX_VALUE) {
            System.out.println("File too large!!");
        }
        byte[] bytes   = new byte[(int) length];
        int    offset  = 0;
        int    numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确认所有的字节都没读取
        if (offset < bytes.length) {
            System.out.println("Could not completely read file " + file.getName());
        }
        is.close();
        return bytes;
    }

    /**
     * 设置边框样式
     *
     * @param table 需要设置表格边框的单元格
     */
    private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());// 必须设置一个TblPr，否则最后会报空指针异常

        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);

        // 获取其内部的TblPr属性设置属性
        table.getTblPr().setTblBorders(borders);
        Jc jc = new Jc();
        jc.setVal(JcEnumeration.CENTER);
        table.getTblPr().setJc(jc);
    }

    /**
     * 设置列宽
     *
     * @param tc
     * @param width
     */
    private static void setCellWidth(Tc tc, int width) {
        TcPr     tableCellProperties = new TcPr();
        TblWidth tableWidth          = new TblWidth();
        tableWidth.setW(BigInteger.valueOf(width));
        tableCellProperties.setTcW(tableWidth);

        tc.setTcPr(tableCellProperties);
    }

    /**
     * 通过设置R设置表格中属性字体加粗，大小为25
     *
     * @param
     */
    private static void setRStyle(R r) {
        // 1.创建一个RPr
        RPr rpr = new RPr();

        // 2.设置RPr
        // 2.1设置字体大小
        HpsMeasure size = new HpsMeasure();
        size.setVal(new BigInteger("25"));
        rpr.setSz(size);
        // 2.2设置加粗
        BooleanDefaultTrue bold = new BooleanDefaultTrue();
        bold.setVal(true);
        rpr.setB(bold);


        // 3.将RPr设置为R的属性
        r.setRPr(rpr);
    }

    /**
     * * @Description: 设置段落水平对齐方式
     */
    public static void setParaJcAlign(P paragraph, JcEnumeration hAlign) {
        if (hAlign != null) {
            PPr pprop = paragraph.getPPr();
            if (pprop == null) {
                pprop = new PPr();
                paragraph.setPPr(pprop);

            }
            Jc align = new Jc();
            align.setVal(hAlign);
            pprop.setJc(align);
        }
    }

    public void mergeCellsHorizontalByGridSpan(Tbl tbl, int row, int fromCell,
                                               int toCell) {
        if (row < 0 || fromCell < 0 || toCell < 0) {
            return;
        }
        List<Tr> trList = getTblAllTr(tbl);
        if (row > trList.size()) {
            return;
        }
        Tr       tr     = trList.get(row);
        List<Tc> tcList = getTrAllCell(tr);
        for (int cellIndex = Math.min(tcList.size() - 1, toCell); cellIndex >= fromCell; cellIndex--) {
            Tc   tc   = tcList.get(cellIndex);
            TcPr tcPr = getTcPr(tc);
            if (cellIndex == fromCell) {
                TcPrInner.GridSpan gridSpan = tcPr.getGridSpan();
                if (gridSpan == null) {
                    gridSpan = new TcPrInner.GridSpan();
                    tcPr.setGridSpan(gridSpan);
                }
                gridSpan.setVal(BigInteger.valueOf(Math.min(tcList.size() - 1,
                        toCell) - fromCell + 1));
            } else {
                tr.getContent().remove(cellIndex);
            }
        }
    }

    /**
     * @Description: 跨列合并
     */
    public static void mergeCellsHorizontal(Tbl tbl, int row, int fromCell, int toCell) {
        if (row < 0 || fromCell < 0 || toCell < 0) {
            return;
        }
        List<Tr> trList = getTblAllTr(tbl);
        if (row > trList.size()) {
            return;
        }
        Tr       tr     = trList.get(row);
        List<Tc> tcList = getTrAllCell(tr);
        for (int cellIndex = fromCell, len = Math
                .min(tcList.size() - 1, toCell); cellIndex <= len; cellIndex++) {
            Tc               tc     = tcList.get(cellIndex);
            TcPr             tcPr   = getTcPr(tc);
            TcPrInner.HMerge hMerge = tcPr.getHMerge();
            if (hMerge == null) {
                hMerge = new TcPrInner.HMerge();
                tcPr.setHMerge(hMerge);
            }
            if (cellIndex == fromCell) {
                hMerge.setVal("restart");
            } else {
                hMerge.setVal("continue");
            }
        }
    }

    /**
     * @Description: 跨行合并
     */
    public void mergeCellsVertically(Tbl tbl, int col, int fromRow, int toRow) {
        if (col < 0 || fromRow < 0 || toRow < 0) {
            return;
        }
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            Tc tc = getTc(tbl, rowIndex, col);
            if (tc == null) {
                break;
            }
            TcPr             tcPr   = getTcPr(tc);
            TcPrInner.VMerge vMerge = tcPr.getVMerge();
            if (vMerge == null) {
                vMerge = new TcPrInner.VMerge();
                tcPr.setVMerge(vMerge);
            }
            if (rowIndex == fromRow) {
                vMerge.setVal("restart");
            } else {
                vMerge.setVal("continue");
            }
        }
    }

    /**
     * @Description:得到指定位置的表格
     */
    public Tc getTc(Tbl tbl, int row, int cell) {
        if (row < 0 || cell < 0) {
            return null;
        }
        List<Tr> trList = getTblAllTr(tbl);
        if (row >= trList.size()) {
            return null;
        }
        List<Tc> tcList = getTrAllCell(trList.get(row));
        if (cell >= tcList.size()) {
            return null;
        }
        return tcList.get(cell);
    }

    /**
     * @Description: 获取所有的单元格
     */
    public static List<Tc> getTrAllCell(Tr tr) {
        List<Object> objList = getAllElementFromObject(tr, Tc.class);
        List<Tc>     tcList  = new ArrayList<Tc>();
        if (objList == null) {
            return tcList;
        }
        for (Object tcObj : objList) {
            if (tcObj instanceof Tc) {
                Tc objTc = (Tc) tcObj;
                tcList.add(objTc);
            }
        }
        return tcList;
    }

    public static TcPr getTcPr(Tc tc) {
        TcPr tcPr = tc.getTcPr();
        if (tcPr == null) {
            tcPr = new TcPr();
            tc.setTcPr(tcPr);
        }
        return tcPr;
    }

    /**
     * @Description: 得到表格所有的行
     */
    public static List<Tr> getTblAllTr(Tbl tbl) {
        List<Object> objList = getAllElementFromObject(tbl, Tr.class);
        List<Tr>     trList  = new ArrayList<Tr>();
        if (objList == null) {
            return trList;
        }
        for (Object obj : objList) {
            if (obj instanceof Tr) {
                Tr tr = (Tr) obj;
                trList.add(tr);
            }
        }
        return trList;
    }

    /**
     * @Description:得到指定类型的元素
     */
    public static List<Object> getAllElementFromObject(Object obj,
                                                       Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement<?>) obj).getValue();
        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }

}