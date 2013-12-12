from xhtml2pdf import pisa

class Page:
  def renderRow(self, items):
    return "<tr><td width='.4in'></td>" + "".join(map(lambda x: x.render(), items)) + "</tr>"
  def group(self, lst, n):
    if len(lst) == 0:
      return []	
    elif len(lst) < n:
      return [lst]
    else:
      return [lst[0:n]] + self.group(lst[n:], n)
  def renderAll(self):
    return "<body><table border='1' width='8.5in' style='text-align: center;'>" + "".join(map(self.renderRow, self.group(self.items, 3))) + "</table></body>"
  def write(self, outfile, verbose=False):
    error = pisa.CreatePDF(self.renderAll(), outfile).err
    if verbose:
      print "An error occurred." if error else "Successfully rendered pdf."
    return error
  def __init__(self, items):
    self.items = items

