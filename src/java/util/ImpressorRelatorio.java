package util;

import banco.Conexao;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ImpressorRelatorio {

    public static void imprimir(String consulta,
            String nomeArquivoJrxml,
            String nomePdfGerado,
            HashMap parametros) throws Exception {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.responseComplete();

        ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
        Connection con = Conexao.get();

        ResultSet rs = con.createStatement().executeQuery(consulta);
        JRResultSetDataSource rjjr = new JRResultSetDataSource(rs);

        JasperReport js = JasperCompileManager.compileReport(context.getRealPath("/WEB-INF/relatorios/"+nomeArquivoJrxml+".jrxml"));

        JasperPrint jp = JasperFillManager.fillReport(js, parametros, rjjr);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jp, baos);

        byte[] bytes = baos.toByteArray();

        if (bytes != null && bytes.length > 0) {
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            // response.setHeader("Content-disposition", "inline;
            // filename=\"relatorioPorData.pdf\"");
            response.setHeader("Content-disposition", "attachment; filename=\"" + nomePdfGerado + ".pdf\"");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
            baos.close();
        }

    }
    
    public static void relatorioConexao( 
			String nomeArquivoJrxml, 
			String nomePdfGerado,
			HashMap parametros) {
		
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();

			facesContext.responseComplete();

			ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();

			Connection conn = Conexao.get();
			//Statement st = conn.createStatement();
			//ResultSet rs = st.executeQuery(consulta);
			//JRResultSetDataSource ds = new JRResultSetDataSource(rs);
			JasperReport js = JasperCompileManager
					.compileReport(context.getRealPath("/WEB-INF/relatorios/"+nomeArquivoJrxml+".jrxml"));

			JasperPrint jp = JasperFillManager.fillReport(js, parametros, conn);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jp, baos);

			byte[] bytes = baos.toByteArray();

			if (bytes != null && bytes.length > 0) {
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
				response.setContentType("application/pdf");
				// response.setHeader("Content-disposition", "inline;
				// filename=\"relatorioPorData.pdf\"");
				response.setHeader("Content-disposition", "attachment; filename=\""+nomePdfGerado+".pdf\"");
				response.setContentLength(bytes.length);
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(bytes, 0, bytes.length);
				outputStream.flush();
				outputStream.close();
				baos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}

}
