package com.deepexi.workflow.svg;


import com.deepexi.workflow.ProcessDiagramCanvas;
import com.deepexi.workflow.ProcessDiagramCanvasFactory;

/**
 *
 */
public class SVGCanvasFactory implements ProcessDiagramCanvasFactory {
    @Override
    public ProcessDiagramCanvas createCanvas(int width, int height, int minX, int minY) {
        return new SVGProcessDiagramCanvas(width, height, minX, minY);
    }
}
