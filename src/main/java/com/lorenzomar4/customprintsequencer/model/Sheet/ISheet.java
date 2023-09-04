package com.lorenzomar4.customprintsequencer.model.Sheet;

import com.lorenzomar4.customprintsequencer.model.PageOfSheet;

public interface ISheet {
     PageOfSheet getFrontSide();
     PageOfSheet getBackSide();
}
