#!/usr/bin/env python
# encoding: utf-8
#基本无bug, 只是第二次计算时要手动把第一次的结果清除
#by needle wang
#2014年 07月 05日 星期六 16:19:33 CST

from Tkinter import Tk, W, E, RIGHT, StringVar
from ttk import Frame, Button, Label, Style, Entry


class Example(Frame):

    CHARS = '1234567890.+-*/()'

    def __init__(self, parent):
        Frame.__init__(self, parent)   

        self.parent = parent

        #在文本区域显示的表达式字符串
        self.eval_buff=StringVar()

        self.initUI()

    def initUI(self):

        self.parent.title("简单计算器 by needle wang")
        Style().configure("TButton", padding=(0,5,0,5), font='serif 10')

        for i in range(4):
            #列属性配置
            self.columnconfigure(i, pad=3)
            #行属性配置
            self.rowconfigure(i, pad=3)
            
        self.rowconfigure(4, pad=3)

        #一行文本域
        entry = Entry(self, textvariable=self.eval_buff)
        entry.grid(row=0, columnspan=4, sticky=W+E)

        cls = Button(self, text="复位(c)", command=self.reset_entry)
        cls.grid(row=1, column=0)
        bck = Button(self, text="退格(back)", command=self.remove_last_one_char)
        bck.grid(row=1, column=1)
        lbl = Button(self, text="占位符", command=self.focus_set)
        lbl.grid(row=1, column=2)
        clo = Button(self, text="退出(q)", command=self.quit)
        clo.grid(row=1, column=3)

        sev = Button(self, text="7", command=lambda: self.setNum("7"))
        sev.grid(row=2, column=0)
        eig = Button(self, text="8", command=lambda: self.setNum("8"))
        eig.grid(row=2, column=1)
        nin = Button(self, text="9", command=lambda: self.setNum("9"))
        nin.grid(row=2, column=2)
        div = Button(self, text="/", command=lambda: self.setNum("/"))
        div.grid(row=2, column=3)

        fou = Button(self, text="4", command=lambda: self.setNum("4"))
        fou.grid(row=3, column=0)
        fiv = Button(self, text="5", command=lambda: self.setNum("5"))
        fiv.grid(row=3, column=1)
        six = Button(self, text="6", command=lambda: self.setNum("6"))
        six.grid(row=3, column=2)
        mul = Button(self, text="*", command=lambda: self.setNum("*"))
        mul.grid(row=3, column=3)

        one = Button(self, text="1", command=lambda: self.setNum("1"))
        one.grid(row=4, column=0)
        two = Button(self, text="2", command=lambda: self.setNum("2"))
        two.grid(row=4, column=1)
        thr = Button(self, text="3", command=lambda: self.setNum("3"))
        thr.grid(row=4, column=2)
        mns = Button(self, text="-", command=lambda: self.setNum("-"))
        mns.grid(row=4, column=3)

        zer = Button(self, text="0", command=lambda: self.setNum("0"))
        zer.grid(row=5, column=0)
        dot = Button(self, text=".", command=lambda: self.setNum("."))
        dot.grid(row=5, column=1)
        equ = Button(self, text="=", command=self.getResult)
        equ.grid(row=5, column=2)
        pls = Button(self, text="+", command=lambda: self.setNum("+"))
        pls.grid(row=5, column=3)

        #获得焦点才能响应按键
        self.focus_set()
        self.bind("<Key>", self.onPress)

        self.pack()

        #self.centerwindow()

    def getResult(self):
        try:
            expression = self.eval_buff.get()
            #如果表达示存在 且 不只是一个点
            if expression and expression != '.':
                result = eval(expression)
                self.eval_buff.set(result)
        except Exception, e:
            self.eval_buff.set(e)

        #将焦点重设为主frame (Example)
        self.focus_set()

    def setNum(self, button_num):
        '''设置显示区域的表达示'''
        if button_num == '.' and '.' in self.eval_buff.get() :
            return
        self.eval_buff.set(self.eval_buff.get() + button_num)

        #将焦点重设为主frame (Example)
        self.focus_set()

    def reset_entry(self):
        self.eval_buff.set('')

        #将焦点重设为主frame (Example)
        self.focus_set()
        
    def remove_last_one_char(self):
        '''退格, 直接 [0:-1] 这样的切片操作不会出现数组下标越界'''
        self.eval_buff.set(self.eval_buff.get()[0:-1])

        #将焦点重设为主frame (Example)
        self.focus_set()


    def onPress(self, event):
        '''增加键盘事件'''
        #非可见字符(长度为0)能通过in判断~
        if event.char:
            #print ord(event.char)
            if event.char == 'q':
                self.quit()
            if event.char == 'c':
                self.reset_entry()
                return
            #敲退格则回删一个字符
            if ord(event.char) == 8:
                self.remove_last_one_char()
                return
            #敲回车则计算表达示
            if ord(event.char) == 13 or event.char == '=':
                self.getResult()
                return
            if event.char in Example.CHARS:
                self.setNum(event.char)

    def centerwindow(self):
        '''窗口居中'''
        #窗口大小
        w = 600
        h = 500

        #屏幕宽高
        sw = self.parent.winfo_screenwidth()
        sh = self.parent.winfo_screenheight()

        x = (sw - w)/2
        y = (sh - h)/2
        self.parent.geometry('%dx%d+%d+%d'%(w, h, x, y))


def main():

    '''没调窗口居中，为什么会自动居中……'''
    root = Tk()
    root.resizable(False, False)
    #大小：长ｘ宽，位置：x+y
    #root.geometry("250x150+300+300")
    Example(root)
    root.mainloop()  


if __name__ == '__main__':
    main()  
