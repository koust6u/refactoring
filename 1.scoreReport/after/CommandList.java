public enum CommandList {
    INITIALIZE(-1), QUIT(0), ADD_STUDENT(1), ADD_SCORE(2), STUDENT_INFO(3), DELETE(4);
    private int index;
    CommandList(int index){
        this.index = index;
    }

    public static CommandList getType(int index){
        for(CommandList command: CommandList.values()){
            if(index == command.index) return command;
        }
        throw new IllegalArgumentException("올바른 명령이 아닙니다.");
    }
}
