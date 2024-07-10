/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DiemDanhDAO;

import DTO.DiemDanhDTO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author duyli
 */
public class DiemDanhBUS {

    public void InsertDD(DiemDanhDTO a) throws SQLException, ParseException {
        new DiemDanhDAO().InsertDiemDanh(a);
    }

    public ArrayList<DiemDanhDTO> LoadDD() throws SQLException, ParseException {
        ArrayList<DiemDanhDTO> a = new ArrayList<>();
        a = new DiemDanhDAO().LoadData();

        return a;
    }
}
