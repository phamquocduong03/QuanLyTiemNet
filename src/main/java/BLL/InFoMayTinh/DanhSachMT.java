package BLL.InFoMayTinh;

import DAL.MayTinhDAO;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMT {
    List <MayTinh> DSMayTinh;
    {
        try {
            DSMayTinh = new MayTinhDAO().getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<MayTinh> getDSMayTinh() {
        return DSMayTinh;
    }
    public void setDSMayTinh(List<MayTinh> DSMayTinh) {
        this.DSMayTinh = DSMayTinh;
    }
    public List<MayTinh> LayDSMayTheoPhong(String phong) throws Exception {
        List<MayTinh> ds = new DanhSachMT().getDSMayTinh();
        List<MayTinh> DSMayTheoPhong = new ArrayList<>();
        for (MayTinh mt : ds) {
            if (mt.getPhong().equals(phong)) {
                DSMayTheoPhong.add(mt);
            }
        }
        return DSMayTheoPhong;
    }
    public int TimMay(String maMay, String phong) {
        for (int i = 0; i < DSMayTinh.size(); i++) {
            MayTinh may = DSMayTinh.get(i);
            if (may.getMaMay().equals(maMay) && may.getPhong().equals(phong)) {
                return i;
            }
        }
        return -1;
    }

    public void ThemMay(MayTinh may) throws Exception {
        DSMayTinh.add(may);
        MayTinhDAO.insertMayTinh(may);
    }

    public void XoaMay(MayTinh mayTinh) throws Exception {
        int index_MayXoa = TimMay(mayTinh.getMaMay(), mayTinh.getPhong());
        if (index_MayXoa != -1 && DSMayTinh.get(index_MayXoa).getPhong().equals(mayTinh.getPhong())) {
            DSMayTinh.remove(index_MayXoa);
            MayTinhDAO.deleteMayTinh(mayTinh);
        } else {
            System.out.println("Lỗi không tìm thấy máy có mã: " + mayTinh.getMaMay() + "và thuộc phòng: " + mayTinh.getPhong());
        }
    }

    public void CapNhatMay(MayTinh may) throws Exception {
        int indexCapNhat = TimMay(may.getMaMay(), may.getPhong());
        if (indexCapNhat != -1) {
            DSMayTinh.set(indexCapNhat, may);
            MayTinhDAO.CapNhatMayTinh(may);
        } else {
            System.out.println("Failed to update MayTinh. MayTinh not found with MAMAY: " + may.getMaMay() + " in PHONG: " + may.getPhong());
        }
    }

}
