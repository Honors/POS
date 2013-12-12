class Item:
  def render(self):
    return "<td style='height: 1.5in'><table><tr><td width='2in'><h1>" + self.header + "</h2><img width='200px' src='" + self.image + "'></td><td width='.35in'></tr></table>" 
  def __init__(self, header, image):
    self.header = header
    self.image = image

