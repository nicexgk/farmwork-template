package com.huawei.cloud.filter;

import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class BufferedResponseWrapper implements HttpServletResponse {

    private HttpServletResponse original;

    private TeeServletOutputStream tee;

    private ByteArrayOutputStream bos;


    public BufferedResponseWrapper(HttpServletResponse response) {

        original = response;

    }

    /**
     * This method is used to get the String content of ByteArrayOutputStream
     *
     * @return
     */
    public String getContent() {

        return bos != null ? bos.toString() : null;

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return PrintWriter
     */
    @Override
    public PrintWriter getWriter() throws IOException {

        return original.getWriter();

    }

    /**
     * This method uses to get the outputStream copy for logging the response
     * body.
     *
     * @return {@link ServletOutputStream}
     */
    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        if (tee == null) {

            bos = new ByteArrayOutputStream();
            // creating two copy of ServletResponse outputStream
            tee = new TeeServletOutputStream(original.getOutputStream(), bos);

        }

        return tee;

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return String
     */
    @Override
    public String getCharacterEncoding() {

        return original.getCharacterEncoding();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return String
     */
    @Override
    public String getContentType() {

        return original.getContentType();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param charset
     */
    @Override
    public void setCharacterEncoding(String charset) {

        original.setCharacterEncoding(charset);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param len
     */
    @Override
    public void setContentLength(int len) {

        original.setContentLength(len);

    }

    @Override
    public void setContentLengthLong(long len) {

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param type
     */
    @Override
    public void setContentType(String type) {

        original.setContentType(type);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param size
     */
    @Override
    public void setBufferSize(int size) {

        original.setBufferSize(size);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return int
     */
    @Override
    public int getBufferSize() {

        return original.getBufferSize();

    }

    /**
     * This method uses TeeServletOutputStream class implementation.
     */
    @Override
    public void flushBuffer() throws IOException {

        tee.flush();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     */
    @Override
    public void resetBuffer() {

        original.resetBuffer();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return bolean
     */
    @Override
    public boolean isCommitted() {

        return original.isCommitted();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     */
    @Override
    public void reset() {

        original.reset();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param loc
     */
    @Override
    public void setLocale(Locale loc) {

        original.setLocale(loc);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @return Locale
     */
    @Override
    public Locale getLocale() {

        return original.getLocale();

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param cookie cookie
     */
    @Override
    public void addCookie(Cookie cookie) {

        original.addCookie(cookie);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name content
     * @return boolean
     */
    @Override
    public boolean containsHeader(String name) {

        return original.containsHeader(name);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param url
     * @return String
     */
    @Override
    public String encodeURL(String url) {

        return original.encodeURL(url);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param url
     * @return String
     */
    @Override
    public String encodeRedirectURL(String url) {

        return original.encodeRedirectURL(url);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param url
     * @return String
     */
    @SuppressWarnings("deprecation")
    @Override
    public String encodeUrl(String url) {

        return original.encodeUrl(url);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param url
     * @return String
     */
    @SuppressWarnings("deprecation")
    @Override
    public String encodeRedirectUrl(String url) {

        return original.encodeRedirectUrl(url);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param sc
     * @param msg
     */
    @Override
    public void sendError(int sc, String msg) throws IOException {

        original.sendError(sc, msg);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param sc
     */
    @Override
    public void sendError(int sc) throws IOException {

        original.sendError(sc);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param location
     */
    @Override
    public void sendRedirect(String location) throws IOException {

        original.sendRedirect(location);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param date
     */
    @Override
    public void setDateHeader(String name, long date) {

        original.setDateHeader(name, date);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param date
     */
    @Override
    public void addDateHeader(String name, long date) {

        original.addDateHeader(name, date);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param value
     */
    @Override
    public void setHeader(String name, String value) {

        original.setHeader(name, value);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param value
     */
    @Override
    public void addHeader(String name, String value) {

        original.addHeader(name, value);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param value
     */
    @Override
    public void setIntHeader(String name, int value) {

        original.setIntHeader(name, value);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param name
     * @param value
     */
    @Override
    public void addIntHeader(String name, int value) {

        original.addIntHeader(name, value);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param sc
     */
    @Override
    public void setStatus(int sc) {

        original.setStatus(sc);

    }

    /**
     * This method uses HttpServletResponse class implementation.
     *
     * @param sc
     * @param sm
     */
    @SuppressWarnings("deprecation")
    @Override
    public void setStatus(int sc, String sm) {

        original.setStatus(sc, sm);

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String name) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }


    public class TeeServletOutputStream extends ServletOutputStream {

        private final TeeOutputStream targetStream;

        /**
         * Initializing TeeOutputStream.
         *
         * @param one
         * @param two
         */
        public TeeServletOutputStream(OutputStream one, OutputStream two) {
            super();
            targetStream = new TeeOutputStream(one, two);

        }

        /**
         * This method is used to write in TeeOutputStream
         *
         * @param arg0
         * @throws IOException
         */
        @Override
        public void write(int arg0) throws IOException {

            this.targetStream.write(arg0);

        }

        /**
         * This method is used to flush TeeoutputStream
         *
         * @throws IOException
         */
        @Override
        public void flush() throws IOException {

            super.flush();

            this.targetStream.flush();

        }

        /**
         * This method is used to close TeeOutputStream
         *
         * @throws IOException
         */
        @Override
        public void close() throws IOException {

            super.close();

            this.targetStream.close();

        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }

}
