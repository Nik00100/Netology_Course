package ru.netology.graphics.image;

public class WindowsTextColorSchema implements TextColorSchema{
    private char[] charSchema = {'#', '$', '@', '%', '*', '+', '-', '|'};
    @Override
    public char convert(int color) {
        int ratio = 255/charSchema.length;
        return (color/ratio == 0) ? charSchema[(color/ratio)] : charSchema[(color/ratio) - 1];
    }
}
