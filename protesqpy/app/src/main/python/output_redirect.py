import sys
from io import StringIO

class OutputRedirect:
    def __init__(self):
        self.output = StringIO()
        self.old_stdout = sys.stdout
        sys.stdout = self.output

    def get_output(self):
        output = self.output.getvalue()
        self.output.truncate(0)
        self.output.seek(0)
        return output

    def reset(self):
        sys.stdout = self.old_stdout 